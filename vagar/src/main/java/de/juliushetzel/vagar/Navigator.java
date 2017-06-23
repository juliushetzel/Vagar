package de.juliushetzel.vagar;


import android.app.Activity;
import android.content.Context;
import android.databinding.Observable;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import de.juliushetzel.vagar.databinding.ObservablePair;

public abstract class Navigator<A extends Activity> extends Observable.OnPropertyChangedCallback {

    private WeakReference<A> mActivityReference;
    // make package local only!! //
    public void attachActivity(A activity){
        mActivityReference = new WeakReference<>(activity);
    }

    public void detachActivity(){
        mActivityReference.clear();
    }

    protected Context getContext(){
       return mActivityReference.get();
    }

    protected A getActivity(){
        return mActivityReference.get();
    }

    public abstract void navigateTo(Class<?> clazz, Bundle bundle);

    @Override
    public void onPropertyChanged(Observable observable, int i) {
        ObservablePair<Class<?>, Bundle> navigationObservable
                = (ObservablePair<Class<?>, Bundle>) observable;
        navigateTo(navigationObservable.getFirst(),
                navigationObservable.getSecond());
    }
}
