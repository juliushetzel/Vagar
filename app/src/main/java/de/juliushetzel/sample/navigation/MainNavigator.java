package de.juliushetzel.sample.navigation;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.view.CompletedTasksFragment;
import de.juliushetzel.sample.view.UncompletedTasksFragment;
import jhetzel.vagar.NavigationEvent;
import jhetzel.vagar.Navigator;

public class MainNavigator extends Navigator {

    @Override
    protected void onIncomingNavigation(@NonNull NavigationEvent navigationEvent) {
        if(getActivity() == null){
            return;
        }

        Class<?> cls = navigationEvent.getComponentClass();
        if(Activity.class.isAssignableFrom(cls)){
            navigateToActivity(cls);
        }else{
            navigateToFragment(cls);
        }
    }

    private void navigateToFragment(Class<?> clazz) {
        FragmentManager fragmentManager = getActivity().getFragmentManager();

        Fragment fragment = null;
        if(clazz == CompletedTasksFragment.class){
            fragment = CompletedTasksFragment.newInstance();
        }else if(clazz == UncompletedTasksFragment.class){
            fragment = UncompletedTasksFragment.newInstance();
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
        Intent intent = new Intent(getActivity(), clazz);
        getActivity().startActivity(intent);
    }
}
