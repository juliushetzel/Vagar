package jhetzel.vagar;

/**
 * The lifecycle of a {@link ViewModel}. The Callbacks in
 * this lifecycle are called to the equally named lifecycle
 * Methods of the {@link android.app.Activity} in which
 * {@link android.app.FragmentManager} the {@link ViewModelHolder}
 * of the bound {@link ViewModel} is in.
 *
 * Important!
 * When using a ViewModel in a Fragment the time when the Callbacks
 * are called will still match those of the {@link android.app.Activity}
 * as the {@link ViewModelHolder}, which is dispatching the Callbacks,
 * is attached to the {@link android.app.Activity} lifecycle.
 *
 */
interface ViewModelLifecycleCallbacks {

    void onStart();

    void onResume();

    void onPause();

    void onStop();

}
