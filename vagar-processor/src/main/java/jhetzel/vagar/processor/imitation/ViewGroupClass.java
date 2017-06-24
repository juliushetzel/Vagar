package jhetzel.vagar.processor.imitation;


final class ViewGroupClass extends ImitatedType {
    @Override
    public String getPackagePath() {
        return "android.view";
    }

    @Override
    public String getSimpleClassName() {
        return "ViewGroup";
    }
}
