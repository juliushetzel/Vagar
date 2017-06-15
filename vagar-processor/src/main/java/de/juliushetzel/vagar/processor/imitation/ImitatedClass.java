package de.juliushetzel.vagar.processor.imitation;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.lang.annotation.Annotation;

public interface ImitatedClass {
    ImitatedClass BUNDLE = new Bundle();
    ImitatedClass DATA_BINDING_UTIL = new DataBindingUtil();
    ImitatedClass VIEW_DATA_BINDING = new ViewDataBinding();
    ImitatedClass VIEW_MODEL_BINDER = new ViewModelBinder();
    ImitatedClass VIEW_MODEL_FACTORY = new ViewModelFactory();

    default String getClassPath(){
        return getPackagePath() + "." + getSimpleClassName();
    }

    default TypeName getClassName(){
        return ClassName.get(getPackagePath(), getSimpleClassName());
    }

    @SuppressWarnings("unchecked")
    default <T extends Annotation> Class<T> asClass() throws ClassNotFoundException {
        return (Class<T>) Class.forName(getClassPath());
    }

    String getPackagePath();

    String getSimpleClassName();
}
