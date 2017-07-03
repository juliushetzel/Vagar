package de.juliushetzel.sample.adapter;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.databinding.ItemTaskListBinding;
import de.juliushetzel.sample.model.Task;
import de.juliushetzel.sample.viewmodel.TaskViewModel;

public class TaskListAdapter extends BaseAdapter {

    private final SelectTaskCallback mSelectTaskCallback;
    private final ObservableList<Task> mTasks;

    public TaskListAdapter(@NonNull SelectTaskCallback selectTaskCallback,
                           @NonNull ObservableList<Task> tasks) {
        mSelectTaskCallback = selectTaskCallback;
        mTasks = tasks;
        mTasks.addOnListChangedCallback(new TaskListPropertyChangeCallback(this));
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemTaskListBinding binding;
        if(convertView == null) {
            binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.item_task_list, parent, false);
            convertView = binding.getRoot();
        } else {
            binding = (ItemTaskListBinding) convertView.getTag();
        }
        Task task = mTasks.get(position);
        TaskViewModel taskViewModel = new TaskViewModel(task, mSelectTaskCallback);
        binding.setViewModel(taskViewModel);
        convertView.setTag(binding);
        return convertView;
    }

    public interface SelectTaskCallback {
        void onSelected(Task task);
    }
}
