package jhetzel.vagar.processor.imitation;


import java.lang.annotation.Annotation;
import java.util.Map;

import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import jhetzel.vagar.processor.exception.VagarAnnotationClassNotFound;

public abstract class ImitatedAnnotation<V extends ImitatedAnnotation.Values> extends ImitatedType {

    public ImitatedAnnotation(String packagePath, String simpleClassName) {
        super(packagePath, simpleClassName);
    }

    /**
     * Suppresses Warning cause we know it is going to be there!
     *
     * @param <T>
     * @return The jhetzel.vagar.annotation.VagarActivity class.
     * @throws VagarAnnotationClassNotFound
     */
    @SuppressWarnings("unchecked")
    public <T extends Annotation> Class<T> getAnnotationClass() throws VagarAnnotationClassNotFound {
        try {
            return (Class<T>) Class.forName(getClassPath());
        } catch (ClassNotFoundException e) {
            throw new VagarAnnotationClassNotFound(e);
        }
    }

    public abstract V getValues(TypeElement annotatedElement);

    public static abstract class Values{
        Values(TypeElement annotatedClass) {
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
