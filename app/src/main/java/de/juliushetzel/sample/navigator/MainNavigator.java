package de.juliushetzel.sample.navigator;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.view.CompletedTasksListFragment;
import de.juliushetzel.sample.view.UncompletedTasksListFragment;
import jhetzel.vagar.Navigator;

public class MainNavigator extends Navigator {

    @Override
    public void navigateTo(@Nullable Class<?> clazz, @Nullable Bundle bundle, @Nullable String action) {
        if(getActivity() == null){
            return;
        }

        if(Activity.class.isAssignableFrom(clazz)){
            navigateToActivity(clazz);
        }else{
            navigateToFragment(clazz);
        }


        // DIskussion fragment ist an activitylifecycle gebundne ->
        // mit support library könnte man FragmentLifeCycleCallbacks registrieren um es an den Fragment Lifecycle zu binden.
    }

    private void navigateToFragment(Class<?> clazz) {
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
    }

    private void navigateToActivity(Class clazz){
        Intent intent = new Intent(getContext(), clazz);
        getActivity().startActivity(intent);
    }
}
