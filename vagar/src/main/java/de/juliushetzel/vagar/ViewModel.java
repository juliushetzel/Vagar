package de.juliushetzel.vagar;

public interface ViewModel extends ViewModelLifecycleCallbacks {

    interface Factory<V extends ViewModel>{
        V createViewModel();
    }
}
