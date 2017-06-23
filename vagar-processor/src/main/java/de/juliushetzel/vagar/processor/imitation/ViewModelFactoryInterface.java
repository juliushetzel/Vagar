package de.juliushetzel.vagar.processor.imitation;


/**
 * This Class's purpose is to mimic the ViewModel.Factory
 * class from de.juliushetzel.vagar package.
 *
 * It is necessary due to the fact, that the Vagar
 * core framework needs a reference to the Android
 * Library which is not supporting annotation
 * processing.
 */
final class ViewModelFactoryInterface extends ImitatedInterface {

    ViewModelFactoryInterface(){}

    @Override
    public String getPackagePath() {
        return "de.juliushetzel.vagar";
    }

    @Override
    public String getSimpleClassName() {
        return "ViewModel.Factory";
    }
}
