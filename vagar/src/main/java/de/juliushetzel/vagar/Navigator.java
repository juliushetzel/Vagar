package de.juliushetzel.vagar;


import android.app.Activity;
import android.content.Context;
import android.databinding.Observable;
import android.os.Bundle;

import java.lang.ref.WeakReference;

import de.juliushetzel.vagar.databinding.ObservablePair;

public abstract class Navigator extends Observable.OnPropertyChangedCallback {

    private WeakReference<Context> mContextReference;

    void attachContext(Context context){
        mContextReference = new WeakReference<>(context);
    }

    void detachContext(){
        mContextReference.clear();
    }

    protected abstract void navigateTo(Context context,
                    Class<? extends Activity> activity,
                    Bundle bundle);

    @Override
    public void onPropertyChanged(Observable observable, int i) {
        ObservablePair<Class<? extends Activity>, Bundle> navigationObservable
                = (ObservablePair<Class<? extends Activity>, Bundle>) observable;
        navigateTo(mContextReference.get(),
                navigationObservable.getFirst(),
                navigationObservable.getSecond());
    }
}
