package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;

import java.lang.annotation.Annotation;

public abstract class MimickedClass {
    public static final MimickedClass BUNDLE = new Bundle();
    public static final MimickedClass DATA_BINDING_UTIL = new DataBindingUtil();

    public String getClassPath(){
        return getPackagePath() + "." + getSimpleClassName();
    }

    public TypeName getClassName(){
        return ClassName.get(getPackagePath(), getSimpleClassName());
    }

    @SuppressWarnings("unchecked")
    public <T extends Annotation> Class<T> getJavaClass() throws ClassNotFoundException {
        return (Class<T>) Class.forName(getClassPath());
    }

    public abstract String getPackagePath();

    public abstract String getSimpleClassName();
}
