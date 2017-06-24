package de.juliushetzel.sample.navigator;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.view.CompletedTasksListFragment;
import de.juliushetzel.sample.view.UncompletedTasksListFragment;
import jhetzel.vagar.Navigator;

public class MainNavigator extends Navigator {

    @Override
    public void navigateTo(@Nullable Class<?> clazz, @Nullable Bundle bundle, @Nullable String action) {
        Fragment fragment = null;
        if(clazz == CompletedTasksListFragment.class){
            fragment = CompletedTasksListFragment.newInstance();
        }else if(clazz == UncompletedTasksListFragment.class){
            fragment = UncompletedTasksListFragment.newInstance();
        }

        if(fragment == null){
            return;
        }

        getActivity().getFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();

        // DIskussion fragment ist an activitylifecycle gebundne ->
        // mit support library könnte man FragmentLifeCycleCallbacks registrieren um es an den Fragment Lifecycle zu binden.
    }
}
