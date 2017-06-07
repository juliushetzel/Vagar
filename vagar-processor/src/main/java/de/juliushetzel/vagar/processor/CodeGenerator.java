package de.juliushetzel.vagar.processor;


import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.classes.ActivityAnnotation;

import static com.squareup.javapoet.ClassName.get;
import static com.squareup.javapoet.MethodSpec.methodBuilder;
import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

public final class CodeGenerator {
    private static final String CLASS_NAME = "Vagar";

    CodeGenerator(TypeElement annotatedClass) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Annotation annotation = annotatedClass.getAnnotation(ActivityAnnotation.getAnnotationClass());

    }

    TypeSpec generateClass() {
        return classBuilder(CLASS_NAME)
                .addModifiers(PUBLIC, FINAL)
                .build();
    }

    private MethodSpec startActivity() {
        return methodBuilder("startActivity")
                .addModifiers(PUBLIC, STATIC)
                .addParameter(get("android.content", "Context"), "context")
                .addStatement("context.startActivity(getIntent(context))")
                .build();
    }
}
