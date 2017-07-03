package de.juliushetzel.sample.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import de.juliushetzel.sample.BR;
import de.juliushetzel.sample.R;
import de.juliushetzel.sample.navigation.MainNavigator;
import de.juliushetzel.sample.viewmodel.TaskListViewModel;
import jhetzel.vagar.Navigator;
import jhetzel.vagar.ViewModel;
import jhetzel.vagar.ViewModelProvider;

public class TaskListActivity extends FragmentActivity implements ViewModel.Factory<TaskListViewModel>, Navigator.Factory{
    private static final String VIEW_MODEL_TAG = "myViewModel_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaskListViewModel viewModel = ViewModelProvider.get(this,this,VIEW_MODEL_TAG,this);
        DataBindingUtil.setContentView(this, R.layout.activity_task_list).setVariable(BR.viewModel, viewModel);
    }

    @Override
    public TaskListViewModel createViewModel() {
        return new TaskListViewModel();
    }

    @NonNull
    @Override
    public Navigator createNavigator() {
        return new MainNavigator();
    }
}
