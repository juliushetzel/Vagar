package de.juliushetzel.vagar.processor.imitation;


import com.squareup.javapoet.TypeName;

import java.util.Map;

import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

final class VagarAnnotation extends ImitatedAnnotation<Imitations.VagarAnnotationValues>{
    @Override
    public String getPackagePath() {
        return "de.juliushetzel.vagar.annotation";
    }

    @Override
    public String getSimpleClassName() {
        return "Assemble";
    }

    @Override
    public Imitations.VagarAnnotationValues getValues(TypeElement annotatedElement) {
        return new Values(annotatedElement);
    }

    private static final class Values extends Imitations.VagarAnnotationValues{
        private static final String KEY_VIEW_MODEL_TAG = "viewModelTag";
        private static final String KEY_VIEW_MODEL = "viewModel";
        private static final String KEY_NAVIGATOR = "navigator";
        private static final String KEY_LAYOUT = "layout";
        private static final String KEY_VIEW_MODEL_TAG_DEFAULT_VALUE = "viewModel";

        private TypeName mViewModelTypeName;
        private int mLayoutResourceId;
        private String mViewModelTag;
        private TypeName mNavigatorTypeName;

        private Values(TypeElement annotatedClass) {
            super(annotatedClass);
        }

        @Override
        public TypeName getViewModelTypeName() {
            return mViewModelTypeName;
        }

        @Override
        public TypeName getNavigatorTypeName() {
            return (mNavigatorTypeName != null ? mNavigatorTypeName : Imitations.Classes.UNASSIGNED_NAVIGATOR.getClassName());
        }

        @Override
        public int getLayoutResourceId() {
            return mLayoutResourceId;
        }

        @Override
        public String getViewModelTag() {
            return (mViewModelTag != null ? mViewModelTag : KEY_VIEW_MODEL_TAG_DEFAULT_VALUE);
        }

        @Override
        protected void extractValues(Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry) {
            String key = entry.getKey().getSimpleName().toString();
            if(key.equals(KEY_VIEW_MODEL)){
                mViewModelTypeName = TypeName.get((TypeMirror) entry.getValue().getValue());
            }else if(key.equals(KEY_LAYOUT)){
                mLayoutResourceId = (Integer) entry.getValue().getValue();
            }else if(key.equals(KEY_VIEW_MODEL_TAG)){
                mViewModelTag = (String) entry.getValue().getValue();
            }else if(key.equals(KEY_NAVIGATOR)){
                mNavigatorTypeName = TypeName.get((TypeMirror) entry.getValue().getValue());
            }
        }
    }
}
