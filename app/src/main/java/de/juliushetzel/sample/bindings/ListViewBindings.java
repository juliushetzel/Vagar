package de.juliushetzel.sample.bindings;


import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.widget.ListView;

import de.juliushetzel.sample.adapter.TaskListAdapter;
import de.juliushetzel.sample.model.Task;

public class ListViewBindings {

    @BindingAdapter({"tasks", "taskCheckListener"})
    public static void setTasks(ListView listView, ObservableList<Task> tasks, TaskListAdapter.SelectTaskCallback listener){
        TaskListAdapter taskListAdapter = new TaskListAdapter(listener, tasks);
        listView.setAdapter(taskListAdapter);
    }
}
