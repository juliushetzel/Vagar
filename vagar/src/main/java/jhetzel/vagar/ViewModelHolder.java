package jhetzel.vagar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;

public final class ViewModelHolder<T extends ViewModelLifecycleCallbacks> extends Fragment {
    private T mViewModel;
    private Navigator mNavigator;

    public ViewModelHolder(){}

    /**
     * Creates a new @link{ViewModelHolder} instance
     * @param viewModel the retaining mViewModel
     * @return an ViewModelHolder instance to addTagReferencePair the mViewModel
     */
    static <T extends ViewModel> ViewModelHolder<T> newInstance(T viewModel, Navigator navigator){
        ViewModelHolder<T> viewModelHolder = new ViewModelHolder<>();
        viewModelHolder.mViewModel = viewModel;
        if(navigator != null) {
            viewModelHolder.mViewModel.mNavigationObservable.addOnPropertyChangedCallback(navigator);
            viewModelHolder.mNavigator = navigator;
        }
        return viewModelHolder;
    }

    /**
     * @return the ViewModel held by the holder
     */
    @Nullable
    T getViewModel() {
        mViewModel.onProvide();
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mNavigator != null) mNavigator.attachActivity(getActivity());
    }

    /* Lifecycle calls */

    @Override
    public void onStart() {
        super.onStart();
        if(mNavigator != null) mNavigator.attachActivity(getActivity());
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
        if(mNavigator != null) mNavigator.detachActivity();
    }
}
