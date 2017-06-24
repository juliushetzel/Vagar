package de.juliushetzel.sample;

import android.databinding.ObservableField;

import de.juliushetzel.sample.second.OtherActivity;
import de.juliushetzel.vagar.ViewModel;

public class MainViewModel extends ViewModel{

    public MainViewModel(){}

    public final ObservableField<String> text = new ObservableField<>("NEU");
    public final ObservableField<String> memoryAddress = new ObservableField<>("...");

    public void doSth(){

    }

    public void swapActivity(){
        navigateTo(OtherActivity.class, null, null);
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
