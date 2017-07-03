package de.juliushetzel.sample.viewmodel.base;


import android.databinding.ObservableList;

import de.juliushetzel.sample.adapter.TaskListAdapter;
import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.AddTaskRepository;
import de.juliushetzel.sample.model.TaskRepositoryImpl;
import jhetzel.vagar.ViewModel;

public abstract class BaseTasksViewModel extends ViewModel implements TaskListAdapter.SelectTaskCallback {

    protected final AddTaskRepository mAddTaskRepository;

    public final ObservableList<Task> tasks;

    public BaseTasksViewModel() {
        mAddTaskRepository = TaskRepositoryImpl.getInstance();
        tasks = loadTasks();
    }

    protected abstract ObservableList<Task> loadTasks();

    @Override
    public void onSelected(Task task) {
        mAddTaskRepository.update(task);
    }
}
