package de.juliushetzel.vagar.processor.imitation;


final class ViewModelInterface extends ImitatedType{

    ViewModelInterface(){}

    @Override
    public String getPackagePath() {
        return "de.juliushetzel.vagar";
    }

    @Override
    public String getSimpleClassName() {
        return "ViewModel";
    }
}
