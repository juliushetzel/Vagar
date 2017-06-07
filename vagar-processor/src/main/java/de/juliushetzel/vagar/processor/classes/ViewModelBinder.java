package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;

public class ViewModelBinder {
    public static final String PACKAGE_PATH = "de.juliushetzel.vagar";
    public static final String CLASS_NAME = "ViewModelBinder";
    public static final String CLASS_PATH = PACKAGE_PATH + "." + CLASS_NAME;

    private ViewModelBinder(){}

    public static ClassName getClassName(){
        return ClassName.get(PACKAGE_PATH, CLASS_NAME);
    }
}
