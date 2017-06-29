package de.juliushetzel.sample.viewmodel;


import android.databinding.ObservableList;

import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.TaskRepository;

public class CompletedTaskListViewModel extends TaskListViewModel{

    public CompletedTaskListViewModel(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected ObservableList<Task> loadTasks() {
        return mTaskRepository.getCompletedTasks();
    }
}
