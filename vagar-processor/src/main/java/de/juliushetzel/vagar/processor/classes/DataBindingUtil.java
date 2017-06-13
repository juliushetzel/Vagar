package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
/**
 * This Class's purpose  is to mimic the DataBindingUtil
 * class from android.databinding package, as annotation
 * processing is not supported by the android library.
 */
public final class DataBindingUtil {
    public static final String PACKAGE_PATH = "android.databinding";
    public static final String CLASS_NAME = "DataBindingUtil";
    public static final String CLASS_PATH = PACKAGE_PATH + "." + CLASS_NAME;

    private DataBindingUtil(){}

    public static TypeName getClassName(){
        return ClassName.get(PACKAGE_PATH, CLASS_NAME);
    }
}
