package de.juliushetzel.vagar.processor.imitation;


final class NavigatorFactoryInterface extends ImitatedInterface{
    @Override
    public String getPackagePath() {
        return "de.juliushetzel.vagar";
    }

    @Override
    public String getSimpleClassName() {
        return "Navigator.Factory";
    }
}
