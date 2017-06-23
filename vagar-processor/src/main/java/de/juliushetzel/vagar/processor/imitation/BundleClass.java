package de.juliushetzel.vagar.processor.imitation;


/**
 * This Class's purpose is to mimic the BundleClass
 * class from android.os package, as annotation
 * processing is not supported by the android
 * library.
 */
final class BundleClass extends ImitatedType {

    BundleClass(){}

    @Override
    public String getPackagePath() {
        return "android.os";
    }

    @Override
    public String getSimpleClassName() {
        return "Bundle";
    }
}
