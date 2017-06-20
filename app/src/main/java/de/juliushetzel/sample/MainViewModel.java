package de.juliushetzel.sample;

import android.databinding.ObservableField;

import de.juliushetzel.sample.second.OtherActivity;
import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.extension.BaseViewModel;

public class MainViewModel extends BaseViewModel implements ViewModel {

    public MainViewModel(){}

    public final ObservableField<String> text = new ObservableField<>("NEU");
    public final ObservableField<String> memoryAddress = new ObservableField<>("...");

    public void doSth(){

    }

    public void swapActivity(){
        navigateTo(OtherActivity.class, null);
    }

    @Override
    public void onStart() {
        memoryAddress.set(this.toString());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
