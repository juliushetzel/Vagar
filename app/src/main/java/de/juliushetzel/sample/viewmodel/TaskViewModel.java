package de.juliushetzel.sample.viewmodel;


import android.databinding.Observable;
import android.databinding.ObservableBoolean;

import de.juliushetzel.sample.adapter.TaskListAdapter;
import de.juliushetzel.sample.model.Task;

public class TaskViewModel extends Observable.OnPropertyChangedCallback{
    public final ObservableBoolean isCompleted;
    public final String text;

    private final Task mTask;
    private final TaskListAdapter.SelectTaskCallback mSelectTaskCallback;

    public TaskViewModel(Task task, TaskListAdapter.SelectTaskCallback selectTaskCallback){
        mTask = task;
        mSelectTaskCallback = selectTaskCallback;
        isCompleted = new ObservableBoolean(task.isCompleted());
        isCompleted.addOnPropertyChangedCallback(this);
        text = task.getText();
    }

    @Override
    public void onPropertyChanged(Observable sender, int propertyId) {
        mTask.setCompleted(isCompleted.get());
        mSelectTaskCallback.onSelected(mTask);
    }
}
