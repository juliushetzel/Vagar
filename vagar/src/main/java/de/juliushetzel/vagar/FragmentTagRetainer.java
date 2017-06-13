package de.juliushetzel.vagar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

final class FragmentTagRetainer extends TagRetainer<Fragment> {
    
    FragmentTagRetainer(@NonNull Fragment target, @NonNull String tag) {
        super(target, tag);
    }

    public FragmentManager.FragmentLifecycleCallbacks asCallbacks(){
        return mFragmentLifecycleCallbacks;
    }

    private final FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentViewCreated(FragmentManager fragmentManager, Fragment fragment, View view, Bundle savedInstanceState) {
            FragmentTagRetainer.super.setReference(fragment, savedInstanceState);
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fragmentManager, Fragment fragment, Bundle outState) {
            FragmentTagRetainer.super.saveTag(fragment, outState);
        }

        @Override
        public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
            FragmentTagRetainer.super.clearReference(fragment);
        }
    };
    
}
