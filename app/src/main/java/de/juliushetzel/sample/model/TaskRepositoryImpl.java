package de.juliushetzel.sample.model;


import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {
    private static TaskRepository sInstance;
    private List<Task> mTasks;

    private TaskRepositoryImpl(){
        mTasks = new ArrayList<>();
        mTasks.add(new Task("Hallo!"));
        mTasks.add(new Task("Huhu!"));
        mTasks.add(new Task("Wie gehts?"));
        mTasks.add(new Task("Alles klar!"));
    }

    public static TaskRepository getInstance(){
        if(sInstance == null){
            sInstance = new TaskRepositoryImpl();
        }
        return sInstance;
    }

    @Override
    public List<Task> getUncompletedTasks() {
        List<Task> uncompletedTasks = new ArrayList<>();
        for(Task task : mTasks){
            if(!task.isCompleted()){
                uncompletedTasks.add(task);
            }
        }
        return uncompletedTasks;
    }

    @Override
    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for(Task task : mTasks){
            if(task.isCompleted()){
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    @Override
    public void update(List<Task> tasks) {
        mTasks = tasks;
    }
}
