package de.juliushetzel.vagar.processor.classes;


import java.util.Map;

import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public abstract class MimickedAnnotation<V extends MimickedAnnotation.Values> extends MimickedClass {

    public abstract V getAnnotationValues(TypeElement annotatedClass);

    abstract static class Values{

        private Values(TypeElement annotatedClass) {
            annotatedClass
                    .getAnnotationMirrors()
                    .stream()
                    .flatMap(annotationMirror ->
                            annotationMirror
                                    .getElementValues()
                                    .entrySet()
                                    .stream())
                    .forEach(this::extractValues);
        }

        protected abstract void extractValues(Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry);
    }
}
