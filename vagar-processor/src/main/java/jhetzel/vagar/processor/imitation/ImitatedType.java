package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.ClassName;

public abstract class ImitatedType {

    public String getClassPath(){
        return getPackagePath() + "." + getSimpleClassName();
    }

    public ClassName getClassName(){
        return ClassName.get(getPackagePath(), getSimpleClassName());
    }

    public abstract String getPackagePath();

    public abstract String getSimpleClassName();
}
