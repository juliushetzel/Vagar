package de.juliushetzel.sample.viewmodel;


import android.databinding.ObservableList;

import de.juliushetzel.sample.adapter.TaskListAdapter;
import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.TaskRepository;
import jhetzel.vagar.ViewModel;

public abstract class TaskListViewModel extends ViewModel implements TaskListAdapter.SelectTaskCallback {
    protected final TaskRepository mTaskRepository;

    public final ObservableList<Task> tasks;

    public TaskListViewModel(TaskRepository taskRepository) {
        mTaskRepository = taskRepository;
        tasks = loadTasks();
    }

    protected abstract ObservableList<Task> loadTasks();

    @Override
    public void onSelected(Task task) {
        mTaskRepository.update(task);
    }
}
