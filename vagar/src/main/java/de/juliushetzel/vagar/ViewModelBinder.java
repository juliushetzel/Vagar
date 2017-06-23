package de.juliushetzel.vagar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class ViewModelBinder {
    private final static TagManager TAG_MANAGER = new TagManager();

    private ViewModelBinder(){}

    public static <V extends ViewModel> V bind(@NonNull Activity activity,
                                        @NonNull ViewModel.Factory<V> factory,
                                        @Nullable Bundle savedInstanceState){
        String tag = TAG_MANAGER.getTag(activity, savedInstanceState);
        return bind(activity, factory, tag);
    }

    static <V extends ViewModel> V bind(@NonNull Activity activity,
                                        @NonNull ViewModel.Factory<V> factory,
                                        @Nullable String tag){
        return ViewModelProvider.get(activity.getFragmentManager(), factory, tag);
    }

    public static <V extends ViewModel> V bind(@NonNull Fragment fragment,
                                               @NonNull ViewModel.Factory<V> factory,
                                               @NonNull String tag){
        return ViewModelProvider.get(fragment.getActivity().getFragmentManager(), factory, tag);
    }

}
