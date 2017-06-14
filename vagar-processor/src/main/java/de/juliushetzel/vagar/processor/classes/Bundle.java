package de.juliushetzel.vagar.processor.classes;


/**
 * This Class's purpose is to mimic the Bundle
 * class from android.os package, as annotation
 * processing is not supported by the android
 * library.
 */
final class Bundle extends MimickedClass{
    private static final String PACKAGE_PATH = "android.os";
    private static final String CLASS_NAME = "Bundle";

    Bundle(){}

    @Override
    public String getPackagePath() {
        return PACKAGE_PATH;
    }

    @Override
    public String getSimpleClassName() {
        return CLASS_NAME;
    }
}
