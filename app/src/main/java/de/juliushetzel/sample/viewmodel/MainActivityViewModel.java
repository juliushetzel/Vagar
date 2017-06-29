package de.juliushetzel.sample.viewmodel;

import android.databinding.ObservableField;

import de.juliushetzel.sample.navigation.TaskListType;
import de.juliushetzel.sample.view.AddTaskActivity;
import de.juliushetzel.sample.view.CompletedTasksListFragment;
import de.juliushetzel.sample.view.UncompletedTasksListFragment;
import jhetzel.vagar.NavigationEvent;
import jhetzel.vagar.ViewModel;

public class MainActivityViewModel extends ViewModel{
    public ObservableField<TaskListType> currentScreen = new ObservableField<>(TaskListType.UNCOMPLETED);

    @Override
    public void onStart() {
        super.onStart();
        if(currentScreen.get() == TaskListType.COMPLETED){
            showCompletedTasks();
        }else{
            showUncompletedTasks();
        }
    }

    public void showCompletedTasks(){
        currentScreen.set(TaskListType.COMPLETED);
        navigateTo(new NavigationEvent().setComponentClass(CompletedTasksListFragment.class));
    }

    public void showUncompletedTasks(){
        currentScreen.set(TaskListType.UNCOMPLETED);
        navigateTo(new NavigationEvent().setComponentClass(UncompletedTasksListFragment.class));
        /*navigation()
                .setClass(UncompletedTasksListFragment.class)
                .start();*/
    }

    public void addTask(){
        navigateTo(new NavigationEvent().setComponentClass(AddTaskActivity.class));
        /*navigation()
                .setClass(AddTaskActivity.class)
                .start();*/
    }
}
