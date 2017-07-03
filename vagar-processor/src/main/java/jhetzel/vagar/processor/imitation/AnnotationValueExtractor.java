package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.ClassName;

import java.util.Map;

import javax.lang.model.element.TypeElement;

import jhetzel.vagar.processor.conditions.TypeChecker;

public interface AnnotationValueExtractor {

    Map<String, Object> extract(TypeElement annotatedElement, ClassName annotation);

    static AnnotationValueExtractor newImplementation(TypeChecker typeChecker){
        return new AnnotationValueExtractorImpl(typeChecker);
    }
}
