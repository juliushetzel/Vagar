package de.juliushetzel.vagar.processor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
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

import de.juliushetzel.vagar.processor.environment.Environment;
import de.juliushetzel.vagar.processor.exception.MissingClassInheritanceException;
import de.juliushetzel.vagar.processor.exception.VagarAnnotationClassNotFound;
import de.juliushetzel.vagar.processor.exception.WrongTypeAnnotatedException;
import de.juliushetzel.vagar.processor.generator.Generator;
import de.juliushetzel.vagar.processor.imitation.Imitations;

import static com.squareup.javapoet.JavaFile.builder;
import static de.juliushetzel.vagar.processor.environment.Environment.FRAMEWORK_BASE_PACKAGE;


@SupportedAnnotationTypes( "de.juliushetzel.vagar.annotation.Vagar" )
@SupportedSourceVersion( SourceVersion.RELEASE_7 )
public class VagarActivityAnnotationProcessor extends AbstractProcessor {

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

        } catch (VagarAnnotationClassNotFound e) {
            mEnvironment.getLog().error("%s -> Vagar Annotation not found: %s",
                    getClass().getSimpleName(),
                    e.getMessage());
        } catch (MissingClassInheritanceException e){
            throw new WrongTypeAnnotatedException(Imitations.Annotations.VAGAR, e);
        }

        return true;
    }

    private List<TypeElement> extractAnnotatedClasses(RoundEnvironment roundEnv) throws VagarAnnotationClassNotFound {
        mEnvironment.getLog().note("%s -> Extracting classes annotated with %s",
                getClass().getSimpleName(),
                Imitations.Annotations.VAGAR.getClassPath());

        List<TypeElement> annotatedElements = roundEnv
                .getElementsAnnotatedWith(Imitations.Annotations.VAGAR.getAnnotationClass())
                .stream()
                .filter((element) -> element.getKind().isClass())
                .map((Function<Element, TypeElement>) element -> (TypeElement) element)
                .collect(Collectors.toList());

        mEnvironment.getLog().note("%s -> Found %s classes annotated with %s",
                getClass().getSimpleName(),
                annotatedElements.size(),
                Imitations.Annotations.VAGAR.getClassPath());

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
