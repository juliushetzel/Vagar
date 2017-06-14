package de.juliushetzel.vagar;


import android.app.Activity;
import android.os.Bundle;

import de.juliushetzel.vagar.databinding.ObservablePair;

public class BaseViewModel implements ViewModel{

    private final ObservablePair<Class<? extends Activity>, Bundle> mNavigationObservable = new ObservablePair<>();

    BaseViewModel(Navigator navigator){
        mNavigationObservable.addOnPropertyChangedCallback(navigator);
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
        //mNavigator.detachContext();
    }

    protected void navigateTo(Class<? extends Activity> activity, Bundle bundle){
        mNavigationObservable.set(activity, bundle);
    }
}
