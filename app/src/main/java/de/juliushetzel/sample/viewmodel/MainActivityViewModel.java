package de.juliushetzel.sample.viewmodel;

import android.databinding.ObservableBoolean;

import de.juliushetzel.sample.view.CompletedTasksListFragment;
import de.juliushetzel.sample.view.UncompletedTasksListFragment;
import jhetzel.vagar.ViewModel;

public class MainActivityViewModel extends ViewModel{

    public final ObservableBoolean showingCompletedTasks = new ObservableBoolean(false);
    public final ObservableBoolean showingUncompletedTasks = new ObservableBoolean(false);

    public void showCompletedTasks(){
        showingCompletedTasks.set(true);
        showingUncompletedTasks.set(false);
        navigateTo(CompletedTasksListFragment.class, null, null);
    }

    public void showUncompletedTasks(){
        showingUncompletedTasks.set(true);
        showingCompletedTasks.set(false);
        navigateTo(UncompletedTasksListFragment.class, null, null);
    }
}
