package de.juliushetzel.sample.viewmodel;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.List;

import de.juliushetzel.sample.adapter.TaskListAdapter;
import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.TaskRepository;
import jhetzel.vagar.ViewModel;

public abstract class TaskListViewModel extends ViewModel implements TaskListAdapter.SelectTaskCallback {
    protected final TaskRepository mTaskRepository;

    public final ObservableList<Task> tasks = new ObservableArrayList<>();

    public TaskListViewModel(TaskRepository taskRepository) {
        mTaskRepository = taskRepository;
    }

    @Override
    public void onProvide() {
        super.onProvide();
        tasks.clear();
        tasks.addAll(loadTasks());
    }

    protected abstract List<Task> loadTasks();

    @Override
    public void onSelected(Task task) {
        tasks.remove(task);
    }
}
