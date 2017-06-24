package de.juliushetzel.sample.model;


import java.util.List;

public interface TaskRepository {
    List<Task> getUncompletedTasks();

    List<Task> getCompletedTasks();

    void update(List<Task> tasks);
}
