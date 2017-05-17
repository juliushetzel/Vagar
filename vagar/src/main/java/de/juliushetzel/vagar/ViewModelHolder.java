package de.juliushetzel.vagar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

public final class ViewModelHolder<T extends ViewModel> extends Fragment {
    private T mViewModel;

    public ViewModelHolder(){}

    /**
     * Creates a new @link{ViewModelHolder} instance
     * @param viewModel the retaining mViewModel
     * @return an ViewModelHolder instance to retain the mViewModel
     */
    static <T extends ViewModel> ViewModelHolder<T> newInstance(T viewModel){
        ViewModelHolder<T> viewModelHolder = new ViewModelHolder<>();
        viewModelHolder.mViewModel = viewModel;
        return viewModelHolder;
    }

    /**
     * @return the ViewModel held by the holder
     */
    @Nullable
    T getViewModel() {
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /* Lifecycle calls */

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.onStop();
    }
}
