package jhetzel.vagar.processor.imitation;


import static jhetzel.vagar.processor.environment.Environment.FRAMEWORK_BASE_PACKAGE;

/**
 * This Class's purpose  is to mimic the ViewModelProviderClass
 * class from jhetzel.vagar package.
 *
 * It is necessary due to the fact, that the Vagar
 * core framework needs a reference to the Android
 * Library which is not supporting annotation
 * processing.
 */
final class ViewModelProviderClass extends ImitatedType {

    ViewModelProviderClass(){}

    @Override
    public String getPackagePath() {
        return FRAMEWORK_BASE_PACKAGE;
    }

    @Override
    public String getSimpleClassName() {
        return "ViewModelProvider";
    }
}
