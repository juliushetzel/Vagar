package jhetzel.vagar.databinding;


import android.databinding.BaseObservable;

public class ObservableTask extends BaseObservable{
    private final Task mTask;
    private boolean mIsExecutable;

    public ObservableTask(Task task){
        mTask = task;
    }

    public ObservableTask(Task task, boolean isExecutable){
        mTask = task;
        mIsExecutable = isExecutable;
    }

    public boolean isExecutable() {
        return mIsExecutable;
    }

    public void setExecutable(boolean executable) {
        mIsExecutable = executable;
        notifyChange();
    }

    public void execute(){
        mTask.onExecute();
    }

    interface Task {
        void onExecute();
    }
}
