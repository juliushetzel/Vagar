package de.juliushetzel.vagar.processor.imitation;


/**
 * This Class's purpose  is to mimic the DataBindingUtilClass
 * class from android.databinding package, as annotation
 * processing is not supported by the android library.
 */
final class DataBindingUtilClass extends ImitatedType {

    DataBindingUtilClass(){}

    @Override
    public String getPackagePath() {
        return "android.databinding";
    }

    @Override
    public String getSimpleClassName() {
        return "DataBindingUtil";
    }
}
