package de.juliushetzel.sample.bindings;


import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.widget.ListView;
import android.widget.TextView;

import de.juliushetzel.sample.adapter.TaskListAdapter;
import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.navigation.TaskListType;

public class BindingAdapters {

    @BindingAdapter({"tasks", "taskCheckListener"})
    public static void setTasks(ListView listView, ObservableList<Task> tasks, TaskListAdapter.SelectTaskCallback listener){
        TaskListAdapter taskListAdapter = new TaskListAdapter(listener, tasks);
        listView.setAdapter(taskListAdapter);
    }

    @BindingAdapter({"enabledForUncompleted"})
    public static void setEnabledForUncompletedTasks(TextView textView, TaskListType taskListType){
        textView.setEnabled(taskListType == TaskListType.UNCOMPLETED);
    }

    @BindingAdapter({"enabledForCompleted"})
    public static void setEnabledForCompletedTasks(TextView textView, TaskListType taskListType){
        textView.setEnabled(taskListType == TaskListType.COMPLETED);
    }

}
