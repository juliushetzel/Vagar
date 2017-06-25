package de.juliushetzel.sample.viewmodel;


import java.util.List;

import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.TaskRepository;

public class CompletedTaskListViewModel extends TaskListViewModel{

    public CompletedTaskListViewModel(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected List<Task> loadTasks() {
        return mTaskRepository.getCompletedTasks();
    }
}
