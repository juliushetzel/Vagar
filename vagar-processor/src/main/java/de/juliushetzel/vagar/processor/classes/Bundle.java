package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
/**
 * This Class's purpose is to mimic the Bundle
 * class from android.os package, as annotation
 * processing is not supported by the android
 * library.
 */
public final class Bundle {
    public static final String PACKAGE_PATH = "android.os";
    public static final String CLASS_NAME = "Bundle";
    public static final String CLASS_PATH = PACKAGE_PATH + "." + CLASS_NAME;

    private Bundle(){}

    public static TypeName getClassName(){
        return ClassName.get(PACKAGE_PATH, CLASS_NAME);
    }
}
