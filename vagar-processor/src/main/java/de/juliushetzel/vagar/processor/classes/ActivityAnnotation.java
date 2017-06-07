package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import de.juliushetzel.vagar.processor.TypeMirrorVisitor;

public class ActivityAnnotation {
    public static final String PACKAGE_PATH = "de.juliushetzel.vagar.annotation";
    public static final String CLASS_NAME = "VagarActivity";
    public static final String CLASS_PATH = PACKAGE_PATH + "." + CLASS_NAME;

    private static final String KEY_VIEW_MODEL = "viewModel";
    private static final String KEY_VIEW_BINDING = "viewBindingClass";

    private ActivityAnnotation(){}

    public static <T extends Annotation> Class<T> getAnnotationClass() throws ClassNotFoundException {
        return (Class<T>) Class.forName(CLASS_PATH);
    }

    public static ClassName getClassName(){
        return ClassName.get(PACKAGE_PATH, CLASS_NAME);
    }

    public static Values getAnnotationValues(TypeElement annotatedClass){
        return new Values(annotatedClass);
    }

    public static class Values{
        private TypeName mViewModelTypeName;
        private TypeName mViewBindingTypeName;

        private Values(TypeElement annotatedClass) {
            List<? extends AnnotationMirror> annotationMirrors = annotatedClass.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    extractValues(entry);
                }
            }
        }

        private void extractValues(Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry){
            TypeMirrorVisitor valueVisitor = new TypeMirrorVisitor();
            entry.getValue().accept(valueVisitor, null);
            String key = entry.getKey().getSimpleName().toString();
            if(key.equals(KEY_VIEW_MODEL)){
                mViewModelTypeName = TypeName.get(valueVisitor.getResult());
            }else if(key.equals(KEY_VIEW_BINDING)){
                mViewBindingTypeName = TypeName.get(valueVisitor.getResult());
            }
        }

        public TypeName getViewModelTypeName() {
            return mViewModelTypeName;
        }

        public TypeName getViewBindingTypeName() {
            return mViewBindingTypeName;
        }
    }
}
