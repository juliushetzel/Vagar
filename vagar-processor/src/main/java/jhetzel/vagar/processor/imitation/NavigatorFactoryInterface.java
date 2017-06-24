package jhetzel.vagar.processor.imitation;


import static jhetzel.vagar.processor.environment.Environment.FRAMEWORK_BASE_PACKAGE;

final class NavigatorFactoryInterface extends ImitatedInterface{
    @Override
    public String getPackagePath() {
        return FRAMEWORK_BASE_PACKAGE;
    }

    @Override
    public String getSimpleClassName() {
        return "Navigator.Factory";
    }
}
