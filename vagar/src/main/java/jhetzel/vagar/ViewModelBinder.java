package jhetzel.vagar;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

@Deprecated
public final class ViewModelBinder {
    private final static TagManager TAG_MANAGER = new TagManager();

    private ViewModelBinder(){}

    public static <B extends ViewDataBinding, V extends ViewModel> B bind(@NonNull Activity activity,
                                                                          @NonNull ViewModel.Factory<V> factory,
                                                                          @Nullable Navigator.Factory navigatorFactory,
                                                                          @NonNull String tag,
                                                                          @IdRes int layoutId,
                                                                          int viewModelBindingId){
        V viewModel = ViewModelProvider.get(activity, factory, tag, navigatorFactory);
        B binding = DataBindingUtil.setContentView(activity, layoutId);
        binding.setVariable(viewModelBindingId, viewModel);
        return binding;
    }

    public static <B extends ViewDataBinding, V extends ViewModel> B bind(@NonNull Fragment fragment,
                                                                          @NonNull ViewModel.Factory<V> factory,
                                                                          @Nullable Navigator.Factory navigatorFactory,
                                                                          @Nullable ViewGroup container,
                                                                          @NonNull String tag,
                                                                          @IdRes int layoutId,
                                                                          int viewModelBindingId){
        V viewModel = ViewModelProvider.get(fragment, factory, tag, navigatorFactory);
        LayoutInflater inflater = LayoutInflater.from(fragment.getActivity());
        B binding = DataBindingUtil.inflate(inflater, layoutId, container, false);
        binding.setVariable(viewModelBindingId, viewModel);
        return binding;
    }

    @Deprecated
    public static <V extends ViewModel> V bind(@NonNull Activity activity,
                                        @NonNull ViewModel.Factory<V> factory,
                                        @Nullable Bundle savedInstanceState){
        String tag = TAG_MANAGER.getTag(activity, savedInstanceState);
        return bind(activity, factory, tag);
    }

    @Deprecated
    static <V extends ViewModel> V bind(@NonNull Activity activity,
                                        @NonNull ViewModel.Factory<V> factory,
                                        @Nullable String tag){
        return ViewModelProvider.get(activity, factory, tag);
    }



    public static <V extends ViewModel> V bind(@NonNull Fragment fragment,
                                               @NonNull ViewModel.Factory<V> factory,
                                               @NonNull String tag){
        return ViewModelProvider.get(fragment, factory, tag);
    }

}
