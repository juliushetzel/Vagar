package de.juliushetzel.sample.viewmodel;


import java.util.List;

import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.model.TaskRepository;

public class UnCompletedTasksViewModel extends TaskListViewModel{

    public UnCompletedTasksViewModel(TaskRepository taskRepository) {
        super(taskRepository);
    }

    @Override
    protected List<Task> loadTasks() {
        return mTaskRepository.getUncompletedTasks();
    }
}
