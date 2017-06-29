package jhetzel.vagar;


import android.app.Activity;
import android.app.Fragment;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import jhetzel.vagar.annotation.Assemble;

/**
 * The frameworks entry point class for each {@link Activity} and {@link Fragment}
 * annotated with {@link jhetzel.vagar.annotation.Assemble}
 */
public class Vagar {

    /**
     * This will hold the generated binder.
     */
    private static InternalBinder sInternalBinder;

    static{
        Vagar.init();
    }

    /**
     * This represents the generated Code for each {@link Activity}
     * annotated with {@link jhetzel.vagar.annotation.Assemble}
     *
     * @param activity the annotated {@link Activity}
     * @param factory the {@link jhetzel.vagar.ViewModel.Factory} which
     *                has to return an instance of {@param <V>}
     * @param <V> the {@link ViewModel} subclass passed to {@link Assemble#viewModel()}.
     * @param <B> the generated {@link ViewDataBinding} subclass corresponding to the
     *           {@link Assemble#layout()}
     * @return {@param <B>}
     */
    public static <V extends ViewModel, B extends ViewDataBinding> B bind(@NonNull Activity activity,
                                                                          @NonNull ViewModel.Factory<V> factory){
        return sInternalBinder.bindInternal(activity, factory);
    }

    /**
     * This represents the generated Code for each {@link Fragment}
     * annotated with {@link jhetzel.vagar.annotation.Assemble}
     *
     * @param fragment the annotated {@link Fragment}
     * @param factory the {@link jhetzel.vagar.ViewModel.Factory} which
     *                has to return an instance of {@param <V>}
     * @param <V> the {@link ViewModel} subclass passed to {@link Assemble#viewModel()}.
     * @param <B> the generated {@link ViewDataBinding} subclass corresponding to the
     *           {@link Assemble#layout()}
     * @return {@param <B>}
     */
    public static <V extends ViewModel, B extends ViewDataBinding> B bind(@NonNull Fragment fragment,
                                                                          @NonNull ViewModel.Factory<V> factory,
                                                                          @Nullable ViewGroup container){
        return sInternalBinder.bindInternal(fragment, factory, container);
    }

    /**
     * The static initialization method that creates an instance of the generated binder.
     */
    private static void init(){
        try {
            sInternalBinder = (InternalBinder) Class.forName("jhetzel.vagar.GeneratedBinder").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This interface will be implemented by the generated binder.
     */
    interface InternalBinder{
        <V extends ViewModel, B extends ViewDataBinding> B bindInternal(Activity activity, ViewModel.Factory<V> factory);
        <V extends ViewModel, B extends ViewDataBinding> B bindInternal(Fragment fragment, ViewModel.Factory<V> factory, ViewGroup container);
    }
}
