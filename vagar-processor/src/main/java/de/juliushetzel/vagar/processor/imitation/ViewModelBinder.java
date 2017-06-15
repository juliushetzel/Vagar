package de.juliushetzel.vagar.processor.imitation;


/**
 * This Class's purpose  is to mimic the ViewModelBinder
 * class from de.juliushetzel.vagar package.
 *
 * It is necessary due to the fact, that the Vagar
 * core framework needs a reference to the Android
 * Library which is not supporting annotation
 * processing.
 */
final class ViewModelBinder implements ImitatedClass {
    private static final String PACKAGE_PATH = "de.juliushetzel.vagar";
    private static final String CLASS_NAME = "ViewModelBinder";

    ViewModelBinder(){}

    @Override
    public String getPackagePath() {
        return PACKAGE_PATH;
    }

    @Override
    public String getSimpleClassName() {
        return CLASS_NAME;
    }
}
