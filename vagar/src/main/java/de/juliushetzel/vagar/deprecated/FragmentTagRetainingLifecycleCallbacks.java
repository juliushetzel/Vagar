package de.juliushetzel.vagar.deprecated;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

public final class FragmentTagRetainingLifecycleCallbacks extends  TagRetainingLifecycleCallbacks<Fragment> {

    public FragmentTagRetainingLifecycleCallbacks(Fragment target, String tag, long id) {
        super(target, tag, id);
    }

    public FragmentManager.FragmentLifecycleCallbacks getCallbacks(){
        return mFragmentLifecycleCallbacks;
    }

    private final FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
        }

        @Override
        public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        }

        @Override
        public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        }

        @Override
        public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        }

        @Override
        public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
            FragmentTagRetainingLifecycleCallbacks.super.onCreate(f, savedInstanceState);
        }

        @Override
        public void onFragmentStarted(FragmentManager fm, Fragment f) {
        }

        @Override
        public void onFragmentResumed(FragmentManager fm, Fragment f) {
        }

        @Override
        public void onFragmentPaused(FragmentManager fm, Fragment f) {
        }

        @Override
        public void onFragmentStopped(FragmentManager fm, Fragment f) {
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
            FragmentTagRetainingLifecycleCallbacks.super.onSaveInstanceState(f, outState);
        }

        @Override
        public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        }

        @Override
        public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        }

        @Override
        public void onFragmentDetached(FragmentManager fm, Fragment f) {
            FragmentTagRetainingLifecycleCallbacks.super.onDestroy(f);
        }
    };
}
