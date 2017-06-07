package de.juliushetzel.vagar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

final class FragmentTagRetainingLifecycleCallbacks extends TagRetainingLifecycleCallbacks<Fragment>{
    
    FragmentTagRetainingLifecycleCallbacks(@NonNull Fragment target, @NonNull String tag) {
        super(target, tag);
    }

    public FragmentManager.FragmentLifecycleCallbacks asCallbacks(){
        return mFragmentLifecycleCallbacks;
    }

    private final FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentViewCreated(FragmentManager fragmentManager, Fragment fragment, View view, Bundle savedInstanceState) {
            FragmentTagRetainingLifecycleCallbacks.super.onCreate(fragment, savedInstanceState);
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fragmentManager, Fragment fragment, Bundle outState) {
            FragmentTagRetainingLifecycleCallbacks.super.onSaveInstanceState(fragment, outState);
        }

        @Override
        public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
            FragmentTagRetainingLifecycleCallbacks.super.onDestroy(fragment);
        }
    };
    
}
