package de.juliushetzel.vagar.processor.imitation;


/**
 * This Class's purpose  is to mimic the ViewDataBinding
 * class from android.databinding package, as annotation
 * processing is not supported by the android library.
 */
final class ViewDataBinding implements ImitatedClass {
    private static final String PACKAGE_PATH = "android.databinding";
    private static final String CLASS_NAME = "ViewDataBinding";

    ViewDataBinding(){}

    @Override
    public String getPackagePath() {
        return PACKAGE_PATH;
    }

    @Override
    public String getSimpleClassName() {
        return CLASS_NAME;
    }
}
