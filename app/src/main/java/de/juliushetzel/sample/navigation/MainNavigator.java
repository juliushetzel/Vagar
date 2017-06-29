package de.juliushetzel.sample.navigation;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.view.CompletedTasksListFragment;
import de.juliushetzel.sample.view.UncompletedTasksListFragment;
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

    /*@Override
    public void onIncomingNavigation(@Nullable Class<?> clazz, @Nullable Bundle bundle, @Nullable String action) {



        // DIskussion fragment ist an activitylifecycle gebundne ->
        // mit support library könnte man FragmentLifeCycleCallbacks registrieren um es an den Fragment Lifecycle zu binden.
    }*/

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
        Intent intent = new Intent(getActivity(), clazz);
        getActivity().startActivity(intent);
    }
}
