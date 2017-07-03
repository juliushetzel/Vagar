package jhetzel.vagar;


import android.app.Activity;
import android.app.Fragment;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import jhetzel.vagar.annotation.Assemble;
import jhetzel.vagar.exception.AssembleAnnotationMissingException;
import jhetzel.vagar.exception.MissingGeneratedBinderClassException;

/**
 * The frameworks entry point class for each {@link Activity} and {@link Fragment}
 * annotated with {@link jhetzel.vagar.annotation.Assemble}
 */
public final class Vagar {

    /**
     * This attribute will hold an instance of the generated binder class.
     */
    private static final InternalBinder sInternalBinder = initInternalBinder();

    /**
     * This represents the generated Code for each {@link Activity}
     * annotated with {@link Assemble} and
     * should be called in {@link Activity#onCreate(Bundle)} as it
     * also sets the activities layout.
     *
     * @param activity the annotated {@link Activity}
     * @param factory the {@link jhetzel.vagar.ViewModel.Factory} which
     *                has to return an instance of {@param <V>}
     * @param <V> the {@link ViewModel} subclass which the {@param factory} will instantiate
     * @param <B> the generated {@link ViewDataBinding} subclass corresponding to the
     *           {@link Assemble#layout()}
     * @return {@param <B>}
     *
     * @throws AssembleAnnotationMissingException if used within an {@link Activity}
     * which is not annotated with {@link Assemble}
     */
    public static <V extends ViewModel, B extends ViewDataBinding> B bind(@NonNull Activity activity,
                                                                          @NonNull ViewModel.Factory<V> factory){
        B binding = sInternalBinder.bindInternal(activity, factory);
        return checkNullOrThrow(binding, activity);
    }

    /**
     * This represents the generated Code for each {@link Fragment}
     * annotated with {@link jhetzel.vagar.annotation.Assemble} and
     * should be called in {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * as it also binds the fragments layout.
     *
     * @param fragment the annotated {@link Fragment}
     * @param factory the {@link jhetzel.vagar.ViewModel.Factory} which
     *                has to return an instance of {@param <V>}
     * @param <V> the {@link ViewModel} subclass which the {@param factory} will instantiate
     * @param <B> the generated {@link ViewDataBinding} subclass corresponding to the
     *           {@link Assemble#layout()}
     * @return {@param <B>}
     *
     * @throws AssembleAnnotationMissingException if used within an {@link Fragment}
     * which is not annotated with {@link Assemble}
     */
    public static <V extends ViewModel, B extends ViewDataBinding> B bind(@NonNull Fragment fragment,
                                                                          @NonNull ViewModel.Factory<V> factory,
                                                                          @Nullable ViewGroup container){
        B binding = sInternalBinder.bindInternal(fragment, factory, container);
        return checkNullOrThrow(binding, fragment);
    }

    /**
     * Method to check the result of the generated Binder for null
     *
     * @throws AssembleAnnotationMissingException if no matching binding Method was generated
     */
    private static <B extends ViewDataBinding> B checkNullOrThrow(B binding, Object object){
        if(binding == null) throw new AssembleAnnotationMissingException(object);
        return binding;
    }

    /**
     * The static initialization method that creates an instance of the generated binder class.
     */
    private static InternalBinder initInternalBinder(){
        try {
            return (InternalBinder) Class.forName("jhetzel.vagar.GeneratedBinder").newInstance();
        } catch (Exception exception) {
            throw new MissingGeneratedBinderClassException(exception);
        }
    }

    /**
     * This interface will be implemented by the generated binder class.
     */
    interface InternalBinder{
        <V extends ViewModel, B extends ViewDataBinding> B bindInternal(Activity activity, ViewModel.Factory<V> factory);
        <V extends ViewModel, B extends ViewDataBinding> B bindInternal(Fragment fragment, ViewModel.Factory<V> factory, ViewGroup container);
    }
}
