package de.juliushetzel.vagar.processor.imitation;


final class InternalBinderInterface extends ImitatedInterface {

    InternalBinderInterface(){}

    @Override
    public String getPackagePath() {
        return "de.juliushetzel.vagar.Vagar";
    }

    @Override
    public String getSimpleClassName() {
        return "InternalBinder";
    }
}
