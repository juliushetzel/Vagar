package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.TypeName;

import javax.lang.model.element.TypeElement;

public abstract class AssembleAnnotationValues extends ImitatedAnnotation.Values{
    AssembleAnnotationValues(TypeElement annotatedClass) {
        super(annotatedClass);
    }

    public abstract TypeName getViewModelTypeName();

    public abstract TypeName getNavigatorTypeName();

    public abstract int getLayoutResourceId();

    public abstract String getViewModelBindingId();

}
