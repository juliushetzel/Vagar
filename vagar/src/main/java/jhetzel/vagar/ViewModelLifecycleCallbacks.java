package jhetzel.vagar;

interface ViewModelLifecycleCallbacks {

    void onProvide();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

}
