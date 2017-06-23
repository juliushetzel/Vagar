package de.juliushetzel.vagar.processor.imitation;


/**
 * This Class's purpose  is to mimic the ViewModelBinderClass
 * class from de.juliushetzel.vagar package.
 *
 * It is necessary due to the fact, that the Vagar
 * core framework needs a reference to the Android
 * Library which is not supporting annotation
 * processing.
 */
final class ViewModelBinderClass extends ImitatedType {
    private static final String PACKAGE_PATH = "de.juliushetzel.vagar";
    private static final String CLASS_NAME = "ViewModelBinder";

    ViewModelBinderClass(){}

    @Override
    public String getPackagePath() {
        return "de.juliushetzel.vagar";
    }

    @Override
    public String getSimpleClassName() {
        return "ViewModelBinder";
    }
}
