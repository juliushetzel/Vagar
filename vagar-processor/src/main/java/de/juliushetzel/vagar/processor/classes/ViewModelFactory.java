package de.juliushetzel.vagar.processor.classes;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.TypeName;
/**
 * This Class is to mimic the ViewModel.Factory class
 * from de.juliushetzel.vagar package.
 *
 * It is necessary due to the fact, that the
 * Vagar core framework needs a reference to the
 * Android Library which is not supporting annotation
 * processing.
 */
public final class ViewModelFactory {
    public static final String PACKAGE_PATH = "de.juliushetzel.vagar";
    public static final String CLASS_NAME = "ViewModel.Factory";
    public static final String CLASS_PATH = PACKAGE_PATH + "." + CLASS_NAME;

    public static TypeName getClassName(){
        return ClassName.get(PACKAGE_PATH, CLASS_NAME);
    }
}
