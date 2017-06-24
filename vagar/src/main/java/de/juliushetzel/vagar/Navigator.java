package de.juliushetzel.vagar;


import android.app.Activity;
import android.content.Context;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import de.juliushetzel.vagar.databinding.ObservableTriplet;

public abstract class Navigator extends Observable.OnPropertyChangedCallback {

    private WeakReference<Activity> mActivityReference;
    void attachActivity(Activity activity){
        mActivityReference = new WeakReference<>(activity);
    }

    void detachActivity(){
        mActivityReference.clear();
    }

    @Nullable
    protected Context getContext(){
       return mActivityReference.get();
    }

    @Nullable
    protected Activity getActivity(){
        return mActivityReference.get();
    }

    public abstract void navigateTo(@Nullable Class<?> clazz, @Nullable Bundle bundle, @Nullable String action);

    @Override
    public void onPropertyChanged(Observable observable, int i) {
        ObservableTriplet<Class<?>, Bundle, String> navigationObservable
                = (ObservableTriplet<Class<?>, Bundle, String>) observable;
        navigateTo(navigationObservable.getFirst(),
                navigationObservable.getSecond(),
                navigationObservable.getThird());
    }

    public abstract static class Unassigned extends Navigator{}

    public interface Factory{
        Navigator createNavigator();
    }
}
