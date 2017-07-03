package de.juliushetzel.sample.view;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.viewmodel.CompletedTasksViewModel;
import jhetzel.vagar.Vagar;
import jhetzel.vagar.ViewModel;
import jhetzel.vagar.annotation.Assemble;

@Assemble(layout = R.layout.fragment_task_list)
public class CompletedTasksFragment extends Fragment implements ViewModel.Factory<CompletedTasksViewModel> {

    public static CompletedTasksFragment newInstance() {
        CompletedTasksFragment fragment = new CompletedTasksFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return Vagar.bind(this, this, container).getRoot();
    }

    @Override
    public CompletedTasksViewModel createViewModel() {
        return new CompletedTasksViewModel();
    }
}
