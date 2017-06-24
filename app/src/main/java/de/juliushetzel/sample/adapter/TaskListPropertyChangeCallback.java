package de.juliushetzel.sample.adapter;


import android.databinding.ObservableList;

import de.juliushetzel.sample.model.Task;

class TaskListPropertyChangeCallback extends ObservableList.OnListChangedCallback<ObservableList<Task>> {

    private final TaskListAdapter mListAdapter;

    TaskListPropertyChangeCallback(TaskListAdapter listAdapter) {
        mListAdapter = listAdapter;
    }

    @Override
    public void onChanged(ObservableList<Task> sender) {
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeChanged(ObservableList<Task> sender, int positionStart, int itemCount) {
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeInserted(ObservableList<Task> sender, int positionStart, int itemCount) {
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeMoved(ObservableList<Task> sender, int fromPosition, int toPosition, int itemCount) {
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(ObservableList<Task> sender, int positionStart, int itemCount) {
        mListAdapter.notifyDataSetChanged();
    }
}
