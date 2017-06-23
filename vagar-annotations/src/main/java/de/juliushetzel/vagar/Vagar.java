package de.juliushetzel.vagar;


import android.app.Activity;
import android.app.Fragment;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

public class Vagar {

    private static InternalBinder sInternalBinder;

    static{
        Vagar.init();
    }

    public static <V extends ViewModel, B extends ViewDataBinding> B bind(@NonNull Activity activity,
                                                                          @NonNull ViewModel.Factory<V> factory){
        return sInternalBinder.bindInternal(activity, factory);
    }

    public static <V extends ViewModel, B extends ViewDataBinding> B bind(@NonNull Fragment fragment,
                                                                          @NonNull ViewModel.Factory<V> factory,
                                                                          @Nullable ViewGroup container){
        return sInternalBinder.bindInternal(fragment, factory, container);
    }

    private static void init(){
        try {
            sInternalBinder = (InternalBinder) Class.forName("de.juliushetzel.vagar.GeneratedBinder").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    interface InternalBinder{
        <V extends ViewModel, B extends ViewDataBinding> B bindInternal(Activity activity, ViewModel.Factory<V> factory);
        <V extends ViewModel, B extends ViewDataBinding> B bindInternal(Fragment fragment, ViewModel.Factory<V> factory, ViewGroup container);
    }
}
