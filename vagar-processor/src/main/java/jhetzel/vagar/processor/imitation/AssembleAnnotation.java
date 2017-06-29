package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.TypeName;

import java.util.Map;

import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import static jhetzel.vagar.processor.environment.Environment.FRAMEWORK_BASE_PACKAGE;

final class AssembleAnnotation extends ImitatedAnnotation<AssembleAnnotationValues>{

    AssembleAnnotation() {
        super(FRAMEWORK_BASE_PACKAGE + ".annotation", "Assemble");
    }

    @Override
    public Values getValues(TypeElement annotatedElement) {
        return new Values(annotatedElement);
    }

    private static final class Values extends AssembleAnnotationValues{
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
        public String getViewModelBindingId() {
            return mViewModelTag == null || mViewModelTag.equals("") ? KEY_VIEW_MODEL_TAG_DEFAULT_VALUE : mViewModelTag;
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
