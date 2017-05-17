package de.juliushetzel.vagar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import static de.juliushetzel.vagar.Conditions.checkRetainInstance;

public final class Vagar {
    private final static TagManager TAG_MANAGER = new TagManager();

    private Vagar(){}

    /* For Activity */

    public static <V extends ViewModel> V bind(@NonNull AppCompatActivity activity,
                                               @NonNull ViewModel.Factory<V> factory,
                                               @Nullable Bundle savedInstanceState){
        return bind(activity, activity.getSupportFragmentManager(), factory, savedInstanceState);
    }

    public static <V extends ViewModel> V bind(@NonNull Activity activity,
                                               @NonNull FragmentManager fragmentManager,
                                               @NonNull ViewModel.Factory<V> factory,
                                               @Nullable Bundle savedInstanceState){
        String tag = TAG_MANAGER.getTag(activity, savedInstanceState);
        return bind(fragmentManager, factory, tag);
    }

    public static <V extends ViewModel> V bind(@NonNull FragmentManager fragmentManager,
                                               @NonNull ViewModel.Factory<V> factory,
                                               @NonNull String tag){
        return ViewModelProvider.get(fragmentManager, factory, tag);
    }

    /* For Fragment */

    public static <V extends ViewModel> V bind(@NonNull Fragment fragment,
                                               @NonNull ViewModel.Factory<V> factory,
                                               @Nullable Bundle savedInstanceState){
        checkRetainInstance(fragment);
        String tag = TAG_MANAGER.getTag(fragment, savedInstanceState);
        return bind(fragment.getChildFragmentManager(), factory, tag);
    }

    public static <V extends ViewModel> V bind(@NonNull Fragment fragment,
                                               @NonNull ViewModel.Factory<V> factory,
                                               @NonNull String tag){
        return bind(fragment.getChildFragmentManager(), factory, tag);
    }

}
