package de.juliushetzel.sample;

import android.databinding.ObservableField;
import android.os.Bundle;

import de.juliushetzel.sample.second.OtherActivity;
import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.databinding.Condition;
import de.juliushetzel.vagar.databinding.ConditionalObservablePair;

public class MainViewModel extends ViewModel{

    public MainViewModel(){}

    public final ObservableField<String> text = new ObservableField<>("NEU");
    public final ObservableField<String> memoryAddress = new ObservableField<>("...");
    public final ConditionalObservablePair<Class, Bundle> navigation = new ConditionalObservablePair<>(Condition.returns(true));

    public void doSth(){

    }

    public void swapActivity(){
        navigation.set(OtherActivity.class, null);
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
