package de.juliushetzel.vagar.processor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
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

import de.juliushetzel.vagar.processor.classes.ActivityAnnotation;
import de.juliushetzel.vagar.processor.environment.Environment;
import de.juliushetzel.vagar.processor.exception.ActivityAnnotationClassNotFoundException;
import de.juliushetzel.vagar.processor.generator.Generator;

import static com.squareup.javapoet.JavaFile.builder;
import static de.juliushetzel.vagar.processor.environment.Environment.FRAMEWORK_ENTRY_POINT_CLASS_NAME;
import static de.juliushetzel.vagar.processor.environment.Environment.FRAMEWORK_ENTRY_POINT_PACKAGE;


@SupportedAnnotationTypes( ActivityAnnotation.CLASS_PATH )
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

            TypeSpec typeSpec = Generator
                    .vagarClassGenerator(mEnvironment)
                    .generate(annotatedElements)
                    .build();

            writeToFile(typeSpec);

        } catch (IOException e) {
            mEnvironment.getLog().error("%s -> Could not generate Class: %s",
                    getClass().getSimpleName(),
                    e.getMessage());

        } catch (ActivityAnnotationClassNotFoundException e) {
            mEnvironment.getLog().error("%s -> ActivityAnnotation class not found: %s",
                    getClass().getSimpleName(),
                    e.getMessage());
        }

        return true;
    }

    private List<TypeElement> extractAnnotatedClasses(RoundEnvironment roundEnv) throws ActivityAnnotationClassNotFoundException {
        mEnvironment.getLog().note("%s -> Extracting classes annotated with %s",
                getClass().getSimpleName(),
                ActivityAnnotation.CLASS_PATH);

        List<TypeElement> annotatedElements = roundEnv
                .getElementsAnnotatedWith(ActivityAnnotation.getAnnotationClass())
                .stream()
                .filter((element) -> element.getKind().isClass())
                .map((Function<Element, TypeElement>) element -> (TypeElement) element)
                .collect(Collectors.toList());

        mEnvironment.getLog().note("%s -> Found %s classes annotated with %s",
                getClass().getSimpleName(),
                annotatedElements.size(),
                ActivityAnnotation.CLASS_PATH);

        return annotatedElements;
    }

    private void writeToFile(TypeSpec typeSpec) throws IOException {
        JavaFile javaFile = builder(FRAMEWORK_ENTRY_POINT_PACKAGE, typeSpec).build();
        mEnvironment.getLog().note("%s -> Attempting to write file %s.%s",
                getClass().getSimpleName(),
                FRAMEWORK_ENTRY_POINT_PACKAGE,
                FRAMEWORK_ENTRY_POINT_CLASS_NAME);

        javaFile.writeTo(processingEnv.getFiler());

        mEnvironment.getLog().note("%s -> File %s.%s successfully written",
                getClass().getSimpleName() ,
                FRAMEWORK_ENTRY_POINT_PACKAGE,
                FRAMEWORK_ENTRY_POINT_CLASS_NAME);

        mSuccessfullyGenerated = true;
    }
}
