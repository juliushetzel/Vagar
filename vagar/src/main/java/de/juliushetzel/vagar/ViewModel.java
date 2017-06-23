package de.juliushetzel.vagar;

public abstract class ViewModel implements ViewModelLifecycleCallbacks {

    @Override public void onStart(){}

    @Override public void onResume(){}

    @Override public void onPause(){}

    @Override public void onStop(){}

    public interface Factory<V extends ViewModel>{
        V createViewModel();
    }
}
