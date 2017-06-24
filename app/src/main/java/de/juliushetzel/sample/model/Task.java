package de.juliushetzel.sample.model;


public class Task {
    private String mText;
    private boolean mIsCompleted;

    public Task(String text){
        mText = text;
    }

    public boolean isCompleted() {
        return mIsCompleted;
    }

    public String getText() {
        return mText;
    }

    public void setCompleted(boolean completed){
        mIsCompleted = completed;
    }

}
