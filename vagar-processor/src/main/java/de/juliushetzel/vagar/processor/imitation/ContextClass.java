package de.juliushetzel.vagar.processor.imitation;


final class ContextClass extends ImitatedType {
    @Override
    public String getPackagePath() {
        return "android.content";
    }

    @Override
    public String getSimpleClassName() {
        return "Context";
    }
}
