package de.juliushetzel.sample.model;


import android.databinding.ObservableList;

public interface AddTaskRepository {
    ObservableList<Task> getUncompletedTasks();

    ObservableList<Task> getCompletedTasks();

    void update(Task task);

    void addTask(Task task);
}
