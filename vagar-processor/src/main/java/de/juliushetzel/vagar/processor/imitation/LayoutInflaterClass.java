package de.juliushetzel.vagar.processor.imitation;


final class LayoutInflaterClass extends ImitatedType {
    @Override
    public String getPackagePath() {
        return "android.view";
    }

    @Override
    public String getSimpleClassName() {
        return "LayoutInflater";
    }
}
