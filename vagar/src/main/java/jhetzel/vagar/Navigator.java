package jhetzel.vagar;


import android.app.Activity;
import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
/**
 * A Navigator that will be bound to the {@link ViewModel#mNavigationObservable}
 * property and react to every call of {@link ViewModel#navigateTo(NavigationEvent)}
 *
 */
public abstract class Navigator extends Observable.OnPropertyChangedCallback {
    private Context mApplicationContext;
    private Activity mReference;

    /**
     * Internal Method to attach an {@link Activity} instance.
     *
     * @param activity the {@link Activity} in which {@link android.app.FragmentManager}
     *                 the {@link ViewModelHolder} of the bound {@link ViewModel} is in.
     */
    final void attachActivity(Activity activity){
        mReference = activity;
        if(mApplicationContext == null){
            mApplicationContext = activity.getApplicationContext();
        }
    }

    /**
     * Internal Method to detach the {@link Activity} instance and prevent
     * Memory Leaks
     */
    final void detachActivity(){
        mReference = null;
    }

    /**
     * @return the {@link Activity} in which {@link android.app.FragmentManager}
     * the {@link ViewModelHolder} of the bound {@link ViewModel} is in. This
     * will be null during the end of {@link ViewModel#onStop()} and the finish of
     * {@link Activity#onCreate(Bundle)}.
     */
    @Nullable
    protected final Activity getActivity(){
        return mReference;
    }

    /**
     * @return the Applications {@link Context}. This will be null until the end of the first
     * call to {@link Activity#onCreate(Bundle)} of the {@link Activity} in which {@link android.app.FragmentManager}
     * the {@link ViewModelHolder} of the bound {@link ViewModel} is in.
     */
    protected final Context getApplicationContext() {
        return mApplicationContext;
    }

    /**
     * The implementation of the {@link android.databinding.Observable.OnPropertyChangedCallback#onPropertyChanged(Observable, int)}
     * to forward the incoming {@link NavigationEvent} to {@link Navigator#onIncomingNavigation(NavigationEvent)}
     * @param sender the {@link ViewModel#mNavigationObservable}
     */
    @Override
    public final void onPropertyChanged(Observable sender, int i) {
        ObservableField<NavigationEvent> observable;
        observable = (ObservableField<NavigationEvent>) sender;
        onIncomingNavigation(observable.get());
    }

    /**
     *
     * @param navigationEvent every {@link NavigationEvent} that is send via
     *                        {@link ViewModel#navigateTo(NavigationEvent)}
     */
    protected abstract void onIncomingNavigation(@NonNull NavigationEvent navigationEvent);

    /**
     * An abstract Factory Interface
     */
    public interface Factory{

        @NonNull
        Navigator createNavigator();
    }
}
