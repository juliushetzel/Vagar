package jhetzel.vagar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import jhetzel.vagar.databinding.ObservableTriplet;

public abstract class ViewModel implements ViewModelLifecycleCallbacks {

    final ObservableTriplet<Class<?>, Bundle, String> mNavigationObservable = new ObservableTriplet<>();

    protected final void navigateTo(@Nullable Class<?> clazz, @Nullable Bundle bundle, @Nullable String action){
        mNavigationObservable.set(clazz, bundle, action);
    }

    @Override public void onStart(){}

    @Override public void onResume(){}

    @Override public void onPause(){}

    @Override public void onStop(){}

    public interface Factory<V extends ViewModel>{
        V createViewModel();
    }
}
