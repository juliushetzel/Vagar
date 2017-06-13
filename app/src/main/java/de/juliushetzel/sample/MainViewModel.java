package de.juliushetzel.sample;

import android.databinding.ObservableField;
import android.os.Bundle;

import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.databinding.ObservablePair;
import de.juliushetzel.sample.second.OtherActivity;

public class MainViewModel implements ViewModel {

    public MainViewModel(){}

    public final ObservableField<String> text = new ObservableField<>("NEU");
    public final ObservableField<String> memoryAddress = new ObservableField<>("...");
    public final ObservablePair<Class<?>, Bundle> navigationObservable = new ObservablePair<>();

    public void doSth(){

    }

    public void swapActivity(){
        navigationObservable.set(OtherActivity.class, null);
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
