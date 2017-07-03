package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.TypeName;

import java.util.Map;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import jhetzel.vagar.processor.environment.Environment;

public final class AssembleAnnotationValues {
    private static final String KEY_VIEW_MODEL_TAG = "bindingTag";
    private static final String KEY_NAVIGATOR = "navigator";
    private static final String KEY_LAYOUT = "layout";
    private static final String KEY_VIEW_MODEL_TAG_DEFAULT_VALUE = "viewModel";

    private final int mLayoutResourceId;
    private final String mViewModelTag;
    private final TypeName mNavigatorTypeName;

    public static AssembleAnnotationValues get(TypeElement annotatedElement, Environment environment){
        Map<String, Object> map = environment
                .getAnnotationValueExtractor()
                .extract(annotatedElement, Imitations.Annotations.ASSEMBLE);
        return new AssembleAnnotationValues(map);
    }

    private AssembleAnnotationValues(Map<String, Object> map) {
        mLayoutResourceId = (Integer) map.get(KEY_LAYOUT);
        mViewModelTag = (String) map.get(KEY_VIEW_MODEL_TAG);
        mNavigatorTypeName = map.get(KEY_NAVIGATOR) == null ? Imitations.Classes.NAVIGATOR : TypeName.get((TypeMirror) map.get(KEY_NAVIGATOR));
    }

    public TypeName getNavigatorTypeName() {
        return mNavigatorTypeName;
    }

    public int getLayoutResourceId() {
        return mLayoutResourceId;
    }

    public String getViewModelBindingId() {
        return mViewModelTag == null || mViewModelTag.equals("") ? KEY_VIEW_MODEL_TAG_DEFAULT_VALUE : mViewModelTag;
    }
}
