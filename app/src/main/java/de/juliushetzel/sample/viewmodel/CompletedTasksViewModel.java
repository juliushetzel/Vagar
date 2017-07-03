package de.juliushetzel.sample.viewmodel;


import android.databinding.ObservableList;

import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.viewmodel.base.BaseTasksViewModel;

public class CompletedTasksViewModel extends BaseTasksViewModel {

    @Override
    protected ObservableList<Task> loadTasks() {
        return mAddTaskRepository.getCompletedTasks();
    }
}
