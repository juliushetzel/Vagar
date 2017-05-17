package de.juliushetzel.vagar;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

final class ActivityTagRetainingLifecycleCallbacks extends TagRetainingLifecycleCallbacks<Activity> implements Application.ActivityLifecycleCallbacks{
    private static final String TAG = ActivityTagRetainingLifecycleCallbacks.class.getSimpleName();
    private static final boolean DEBUG = true;

    public ActivityTagRetainingLifecycleCallbacks(Activity target, String tag) {
        super(target, tag);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(DEBUG) Log.d(TAG, "onActivityCreated");
        super.onCreate(activity, savedInstanceState);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if(DEBUG) Log.d(TAG, "onActivitySaveInstanceState");
        super.onSaveInstanceState(activity, outState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        super.onDestroy(activity);
    }
}
