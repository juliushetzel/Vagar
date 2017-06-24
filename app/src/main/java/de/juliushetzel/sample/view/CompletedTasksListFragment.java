package de.juliushetzel.sample.view;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.model.TaskRepositoryImpl;
import de.juliushetzel.sample.viewmodel.CompletedTaskListViewModel;
import jhetzel.vagar.Vagar;
import jhetzel.vagar.ViewModel;
import jhetzel.vagar.annotation.Assemble;

@Assemble(
        viewModel = CompletedTaskListViewModel.class,
        layout = R.layout.fragment_task_list
)
public class CompletedTasksListFragment extends Fragment implements ViewModel.Factory<ViewModel> {

    public static CompletedTasksListFragment newInstance() {
        CompletedTasksListFragment fragment = new CompletedTasksListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return Vagar.bind(this, this, container).getRoot();
    }

    @Override
    public ViewModel createViewModel() {
        return new CompletedTaskListViewModel(TaskRepositoryImpl.getInstance());
    }
}
