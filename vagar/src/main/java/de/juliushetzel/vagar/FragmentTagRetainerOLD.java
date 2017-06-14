package de.juliushetzel.vagar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

final class FragmentTagRetainerOLD extends BundleTagInjector<Fragment> {

    FragmentTagRetainerOLD(String bundleTagKey) {
        super(bundleTagKey);
    }

    public FragmentManager.FragmentLifecycleCallbacks asCallbacks(){
        return mFragmentLifecycleCallbacks;
    }

    private final FragmentManager.FragmentLifecycleCallbacks mFragmentLifecycleCallbacks = new FragmentManager.FragmentLifecycleCallbacks() {
        @Override
        public void onFragmentViewCreated(FragmentManager fragmentManager, Fragment fragment, View view, Bundle savedInstanceState) {
            FragmentTagRetainerOLD.super.referenceTarget(fragment, savedInstanceState);
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fragmentManager, Fragment fragment, Bundle outState) {
            FragmentTagRetainerOLD.super.injectTag(fragment, outState);
        }

        @Override
        public void onFragmentStopped(FragmentManager fragmentManager, Fragment fragment) {
            FragmentTagRetainerOLD.super.dereference(fragment);
        }

        @Override
        public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
            FragmentTagRetainerOLD.super.dereference(fragment);
        }
    };
    
}
