package jhetzel.vagar.processor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import jhetzel.vagar.processor.environment.Environment;
import jhetzel.vagar.processor.exception.AssembleAnnotationClassNotFoundException;
import jhetzel.vagar.processor.exception.MissingClassInheritanceException;
import jhetzel.vagar.processor.exception.WrongTypeAnnotatedException;
import jhetzel.vagar.processor.generator.Generator;
import jhetzel.vagar.processor.imitation.Imitations;

import static com.squareup.javapoet.JavaFile.builder;
import static jhetzel.vagar.processor.environment.Environment.FRAMEWORK_BASE_PACKAGE;


@SupportedAnnotationTypes( "jhetzel.vagar.annotation.Assemble" )
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class AssembleAnnotationProcessor extends AbstractProcessor {

    private Environment mEnvironment;

    private boolean mSuccessfullyGenerated;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mEnvironment = Environment.newImplementation(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if(mSuccessfullyGenerated){
            return true;
        }

        try {
            List<TypeElement> annotatedElements = extractAnnotatedClasses(roundEnv);

            if(annotatedElements.isEmpty()){
                return true;
            }
            mEnvironment.getInheritanceChecker().checkInheritance(
                    annotatedElements,
                    Imitations.Classes.ACTIVITY,
                    Imitations.Classes.FRAGMENT
            );

            List<TypeSpec> generatedClasses = new ArrayList<>();

            generatedClasses.add(Generator
                    .forEntryPointClass(mEnvironment)
                    .generate(annotatedElements)
                    .build());

            for (TypeSpec typeSpec : generatedClasses) {
                writeToFile(typeSpec);
            }

        } catch (IOException e) {
            mEnvironment.getLog().error("%s -> Could not generate Class: %s",
                    getClass().getSimpleName(),
                    e.getMessage());

        } catch (AssembleAnnotationClassNotFoundException e) {
            mEnvironment.getLog().error("%s -> Assemble Annotation not found: %s",
                    getClass().getSimpleName(),
                    e.getMessage());
        } catch (MissingClassInheritanceException e){
            throw new WrongTypeAnnotatedException(Imitations.Annotations.ASSEMBLE, e);
        }

        return true;
    }

    private List<TypeElement> extractAnnotatedClasses(RoundEnvironment roundEnv) throws AssembleAnnotationClassNotFoundException {
        mEnvironment.getLog().note("%s -> Extracting classes annotated with %s",
                getClass().getSimpleName(),
                Imitations.Annotations.ASSEMBLE);

        Class<? extends Annotation> annotationClass;
        try {
            annotationClass = (Class<? extends Annotation>) Class.forName(Imitations.Annotations.ASSEMBLE.reflectionName());
        } catch (ClassNotFoundException e) {
            throw new AssembleAnnotationClassNotFoundException(e);
        }

        List<TypeElement> annotatedElements = roundEnv
                .getElementsAnnotatedWith(annotationClass)
                .stream()
                .filter((element) -> element.getKind().isClass())
                .map((Function<Element, TypeElement>) element -> (TypeElement) element)
                .collect(Collectors.toList());

        mEnvironment.getLog().note("%s -> Found %s classes annotated with %s",
                getClass().getSimpleName(),
                annotatedElements.size(),
                Imitations.Annotations.ASSEMBLE);

        return annotatedElements;
    }

    private void writeToFile(TypeSpec typeSpec) throws IOException {
        JavaFile javaFile = builder(FRAMEWORK_BASE_PACKAGE, typeSpec).build();
        mEnvironment.getLog().note("%s -> Attempting to write file %s",
                getClass().getSimpleName(),
                typeSpec.name);

        javaFile.writeTo(processingEnv.getFiler());

        mEnvironment.getLog().note("%s -> File %s successfully written",
                getClass().getSimpleName(),
                typeSpec.name);

        mSuccessfullyGenerated = true;
    }
}
