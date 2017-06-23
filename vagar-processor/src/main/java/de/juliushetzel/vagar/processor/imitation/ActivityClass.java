package de.juliushetzel.vagar.processor.imitation;


final class ActivityClass extends ImitatedType {

    ActivityClass(){}

    @Override
    public String getPackagePath() {
        return "android.app";
    }

    @Override
    public String getSimpleClassName() {
        return "Activity";
    }
}
