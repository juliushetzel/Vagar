package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import de.juliushetzel.vagar.processor.exception.ActivityAnnotationClassNotFoundException;

/**
 * This Class is to mimic the VagarActivity annotation
 * in de.juliushetzel.vagar.annotation package.
 *
 * It is necessary due to the fact, that the
 * VagarActivity Annotation needs a reference to the
 * Android Library which is not supporting annotation
 * processing.
 */
public final class ActivityAnnotation {
    public static final String PACKAGE_PATH = "de.juliushetzel.vagar.annotation";
    public static final String CLASS_NAME = "VagarActivity";
    public static final String CLASS_PATH = PACKAGE_PATH + "." + CLASS_NAME;

    private static final String KEY_VIEW_MODEL = "viewModel";
    private static final String KEY_VIEW_MODEL_TAG = "viewModelTag";
    private static final String KEY_LAYOUT = "layout";

    private static final String KEY_VIEW_MODEL_TAG_DEFAULT_VALUE = "viewModel";

    private ActivityAnnotation(){}

    /**
     * return the de.juliushetzel.vagar.annotation.VagarActivity class.
     * suppresses Warning cause we know it is going to be there!
     * @param <T>
     * @return
     * @throws ActivityAnnotationClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T extends Annotation> Class<T> getAnnotationClass() throws ActivityAnnotationClassNotFoundException {
        try {
            return (Class<T>) Class.forName(CLASS_PATH);
        } catch (ClassNotFoundException e) {
            throw new ActivityAnnotationClassNotFoundException(e);
        }
    }

    public static TypeName getClassName(){
        return ClassName.get(PACKAGE_PATH, CLASS_NAME);
    }

    /**
     * Returns the values given in the "real" annotation by the user.
     * @param annotatedClass
     * @return
     */
    public static Values getAnnotationValues(TypeElement annotatedClass){
        return new Values(annotatedClass);
    }

    /**
     * A Class to hold the "real" values given in the annotation
     */
    public static class Values{
        private TypeName mViewModelTypeName;
        private int mLayoutResourceId;
        private String mViewModelTag;

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

        private void extractValues(Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry){
            String key = entry.getKey().getSimpleName().toString();
            if(key.equals(KEY_VIEW_MODEL)){
                mViewModelTypeName = TypeName.get((TypeMirror) entry.getValue().getValue());
            }else if(key.equals(KEY_LAYOUT)){
                mLayoutResourceId = (Integer) entry.getValue().getValue();
            }else if(key.equals(KEY_VIEW_MODEL_TAG)){
                mViewModelTag = (String) entry.getValue().getValue();
            }
        }

        public TypeName getViewModelTypeName() {
            return mViewModelTypeName;
        }

        public int getLayoutResourceId() {
            return mLayoutResourceId;
        }

        public String getViewModelTag(){
            return (mViewModelTag != null ? mViewModelTag : KEY_VIEW_MODEL_TAG_DEFAULT_VALUE);
        }
    }
}
