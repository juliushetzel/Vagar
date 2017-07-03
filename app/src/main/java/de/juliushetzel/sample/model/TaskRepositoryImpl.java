package de.juliushetzel.sample.model;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

public class TaskRepositoryImpl implements AddTaskRepository {
    private static AddTaskRepository sInstance;
    private ObservableList<Task> mCompletedTasks;
    private ObservableList<Task> mUnCompletedTasks;

    private TaskRepositoryImpl(){
        mUnCompletedTasks = new ObservableArrayList<>();
        mCompletedTasks = new ObservableArrayList<>();
        mUnCompletedTasks.add(new Task("Ein Wolf"));
        mUnCompletedTasks.add(new Task("Ein Schaf"));
        mUnCompletedTasks.add(new Task("Ein Kohlkopf"));
    }

    public static AddTaskRepository getInstance(){
        if(sInstance == null){
            sInstance = new TaskRepositoryImpl();
        }
        return sInstance;
    }

    @Override
    public ObservableList<Task> getUncompletedTasks() {
        return mUnCompletedTasks;
    }

    @Override
    public ObservableList<Task> getCompletedTasks() {
        return mCompletedTasks;
    }

    @Override
    public void update(Task task) {
        if(task.isCompleted()){
            mUnCompletedTasks.remove(task);
            mCompletedTasks.add(task);
        }else{
            mCompletedTasks.remove(task);
            mUnCompletedTasks.add(task);
        }
    }

    @Override
    public void addTask(Task task) {
        mUnCompletedTasks.add(task);
    }
}
