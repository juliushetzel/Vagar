package de.juliushetzel.sample.viewmodel;


import android.databinding.ObservableList;

import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.TaskRepository;

public class UnCompletedTasksViewModel extends TaskListViewModel{

    public UnCompletedTasksViewModel(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected ObservableList<Task> loadTasks() {
        return mTaskRepository.getUncompletedTasks();
    }
}
