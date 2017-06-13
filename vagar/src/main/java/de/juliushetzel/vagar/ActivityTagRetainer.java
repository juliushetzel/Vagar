package de.juliushetzel.vagar;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

final class ActivityTagRetainer extends TagRetainer<Activity> implements Application.ActivityLifecycleCallbacks{
    private static final String TAG = ActivityTagRetainer.class.getSimpleName();
    private static final boolean DEBUG = true;

    public ActivityTagRetainer(Activity target,
                               String tag) {
        super(target, tag);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if(DEBUG) Log.d(TAG, "onActivityCreated, " + activity.getClass().getSimpleName());
        super.setReference(activity, savedInstanceState);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if(DEBUG) Log.d(TAG, "onActivityStarted, " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        if(DEBUG) Log.d(TAG, "onActivityResumed, " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if(DEBUG) Log.d(TAG, "onActivityPaused, " + activity.getClass().getSimpleName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if(DEBUG) Log.d(TAG, "onActivityStopped, " + activity.getClass().getSimpleName());
        if(activity.isChangingConfigurations()) {
            super.clearReference(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if(DEBUG) Log.d(TAG, "onActivitySaveInstanceState, " + activity.getClass().getSimpleName());
        super.saveTag(activity, outState);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if(DEBUG) Log.d(TAG, "onActivityDestroyed, " + activity.getClass().getSimpleName());
        super.clearReference(activity);
        if(!activity.isChangingConfigurations()){
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }
}
