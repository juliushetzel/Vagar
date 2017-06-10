package de.juliushetzel.vagar.sample;

import android.databinding.ObservableField;

import de.juliushetzel.vagar.ViewModel;

public class MainViewModel implements ViewModel {

    public MainViewModel(){}

    private int clicks = 0;

    private boolean mActive;

    public final ObservableField<String> text = new ObservableField<>("NEU");

    public void doSth(){

    }

    @Override
    public void onStart() {

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
