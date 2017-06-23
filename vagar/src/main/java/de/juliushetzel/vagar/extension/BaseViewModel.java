package de.juliushetzel.vagar.extension;


import android.app.Activity;
import android.os.Bundle;

import de.juliushetzel.vagar.Navigator;
import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.databinding.ObservablePair;

public abstract class BaseViewModel extends ViewModel {

    private final ObservablePair<Class<? extends Activity>, Bundle> mNavigationObservable = new ObservablePair<>();

    private Navigator mNavigator;

    // make package local!!
    public void setNavigator(Navigator navigator){
        mNavigator = navigator;
        mNavigationObservable.addOnPropertyChangedCallback(navigator);
    }

    public Navigator getNavigator(){
        return mNavigator;
    }

    protected void navigateTo(Class<? extends Activity> activity, Bundle bundle){
        mNavigationObservable.set(activity, bundle);
    }
}
