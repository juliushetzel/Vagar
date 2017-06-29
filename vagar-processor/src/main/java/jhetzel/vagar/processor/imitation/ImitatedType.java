package jhetzel.vagar.processor.imitation;


import com.squareup.javapoet.ClassName;

public class ImitatedType {
    private final String mPackagePath;
    private final String mSimpleClassName;

    public ImitatedType(String packagePath, String simpleClassName) {
        mPackagePath = packagePath;
        mSimpleClassName = simpleClassName;
    }

    public String getClassPath(){
        return mPackagePath + "." + mSimpleClassName;
    }

    public ClassName getClassName(){
        return ClassName.get(mPackagePath, mSimpleClassName);
    }

    public String getPackagePath(){
        return mPackagePath;
    }

    public String getSimpleClassName(){
        return mSimpleClassName;
    };
}
