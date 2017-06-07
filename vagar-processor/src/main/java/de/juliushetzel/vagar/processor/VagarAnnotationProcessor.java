package de.juliushetzel.vagar.processor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

import de.juliushetzel.vagar.processor.classes.ActivityAnnotation;

import static com.squareup.javapoet.JavaFile.builder;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;


@SupportedAnnotationTypes( ActivityAnnotation.CLASS_PATH )
@SupportedSourceVersion( SourceVersion.RELEASE_7 )
public class VagarAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<MethodSpec> methods = new ArrayList<>();

        try {
            for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(ActivityAnnotation.getAnnotationClass())) {

                // annotation is only allowed on classes, so we can safely cast here
                TypeElement annotatedClass = (TypeElement) annotatedElement;
                ActivityAnnotation.Values values = ActivityAnnotation.getAnnotationValues(annotatedClass);
                methods.add(MethodSpec
                        .methodBuilder("bind")
                        .addParameter(values.getViewBindingTypeName(), "viewBinding")
                        .addParameter(values.getViewModelTypeName(), "viewModel")
                        .build()
                );

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        TypeSpec.Builder builder = classBuilder("HALLO")
                .addModifiers(PUBLIC, FINAL);

        for(MethodSpec methodSpec : methods){
            builder.addMethod(methodSpec);
        }

        TypeSpec type = builder
                .build();

        JavaFile javaFile = builder("de.juliushetzel.vagar", type).build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*try {
            for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(ActivityAnnotation.getAnnotationClass())) {

                // annotation is only allowed on classes, so we can safely cast here
                TypeElement annotatedClass = (TypeElement) annotatedElement;
                generateCode(annotatedClass);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return true;
    }

    private void generateCode(TypeElement annotatedClass) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String packageName = getPackageName(processingEnv.getElementUtils(), annotatedClass);

        CodeGenerator codeGenerator = new CodeGenerator(annotatedClass);
        TypeSpec generatedClass = codeGenerator.generateClass();

        JavaFile javaFile = builder(packageName, generatedClass).build();
        javaFile.writeTo(processingEnv.getFiler());
    }

    static String getPackageName(Elements elementUtils, TypeElement type){
        PackageElement pkg = elementUtils.getPackageOf(type);
        return pkg.getQualifiedName().toString();
    }
}
