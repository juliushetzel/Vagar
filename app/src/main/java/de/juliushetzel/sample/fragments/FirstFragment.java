package de.juliushetzel.sample.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.databinding.FragmentSubBinding;
import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.annotation.Vagar;

@Vagar(
        viewModel = FirstViewModel.class,
        layout = R.layout.fragment_sub
)
public class FirstFragment extends Fragment implements ViewModel.Factory<FirstViewModel>{

    FragmentSubBinding mViewBinding;

    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = de.juliushetzel.vagar.Vagar.bind(this, this, container);
        return mViewBinding.getRoot();
    }

    @Override
    public FirstViewModel createViewModel() {
        return new FirstViewModel();
    }
}
