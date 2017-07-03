package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.ClassName;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;

import jhetzel.vagar.processor.conditions.TypeChecker;

class AnnotationValueExtractorImpl implements AnnotationValueExtractor{
    private final TypeChecker mTypeChecker;

    public AnnotationValueExtractorImpl(TypeChecker typeChecker) {
        mTypeChecker = typeChecker;
    }

    @Override
    public Map<String, Object> extract(TypeElement annotatedElement, ClassName annotation) {
        Map<String, Object> result = new HashMap<>();
        annotatedElement
                .getAnnotationMirrors()
                .stream()
                .filter((Predicate<AnnotationMirror>) annotationMirror -> mTypeChecker.isSameType(annotationMirror, annotation))
                .flatMap(annotationMirror ->
                        annotationMirror
                                .getElementValues()
                                .entrySet()
                                .stream())
                .forEach(entry -> result.put(entry.getKey().getSimpleName().toString(), entry.getValue().getValue()));
        return result;
    }
}
