package de.juliushetzel.vagar.processor.imitation;


final class EntryPointClass extends ImitatedType {

    EntryPointClass(){}

    @Override
    public String getPackagePath() {
        return "de.juliushetzel.vagar";
    }

    @Override
    public String getSimpleClassName() {
        return "Vagar";
    }
}
