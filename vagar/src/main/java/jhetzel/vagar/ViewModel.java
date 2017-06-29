package jhetzel.vagar;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

/**
 *

 */
public abstract class ViewModel implements ViewModelLifecycleCallbacks {

    private final ObservableField<NavigationEvent> mNavigationObservable = new ObservableField<>();

    final ObservableField<NavigationEvent> getNavigationObservable(){
        return mNavigationObservable;
    }

    protected void navigateTo(NavigationEvent navigationEvent){
        mNavigationObservable.set(navigationEvent);
    }

    @Override public void onStart(){}

    @Override public void onResume(){}

    @Override public void onPause(){}

    @Override public void onStop(){}

    public interface Factory<V extends ViewModel>{

        @NonNull
        V createViewModel();
    }
}
