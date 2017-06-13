package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;
/**
 * This Class's purpose  is to mimic the ViewModelBinder
 * class from de.juliushetzel.vagar package.
 *
 * It is necessary due to the fact, that the Vagar
 * core framework needs a reference to the Android
 * Library which is not supporting annotation
 * processing.
 */
public final class ViewModelBinder {
    public static final String PACKAGE_PATH = "de.juliushetzel.vagar";
    public static final String CLASS_NAME = "ViewModelBinder";
    public static final String CLASS_PATH = PACKAGE_PATH + "." + CLASS_NAME;

    private ViewModelBinder(){}

    public static ClassName getClassName(){
        return ClassName.get(PACKAGE_PATH, CLASS_NAME);
    }
}
