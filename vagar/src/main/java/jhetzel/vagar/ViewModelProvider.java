package jhetzel.vagar;

import android.support.annotation.NonNull;
import android.app.FragmentManager;
import android.support.annotation.Nullable;

public final class ViewModelProvider {

    /**
     * Returns the Config Persistent View Model or creates one if not existing
     * and saves it under the given {@param tag}
     *
     * @param fragmentManager Needed for the view model to get saved in a Retained Fragment
     * @param factory Needed to get a new instance of the view model
     * @param tag The tag under which the view model holding fragment will be saved in the fragment manager
     * @param <T> extends @link{ViewModel} the view model
     * @return the retained view model
     */
    public static <T extends ViewModel> T get(@NonNull FragmentManager fragmentManager,
                                       @NonNull ViewModel.Factory<T> factory,
                                       @NonNull String tag) {

        @SuppressWarnings("unchecked")
        ViewModelHolder<T> viewModelHolder = getViewModelHolder(fragmentManager, factory, tag, null);
        return viewModelHolder.getViewModel();
    }


    /**
     * Returns the Config Persistent View Model or creates one if not existing
     * and saves it under the given {@param tag}
     *
     * @param fragmentManager Needed for the view model to get saved in a Retained Fragment
     * @param factory Needed to get a new instance of the view model
     * @param tag The tag under which the view model holding fragment will be saved in the fragment manager
     * @param <T> extends @link{ViewModel} the view model
     * @return the retained view model
     */
    public static <T extends ViewModel> T get(@NonNull FragmentManager fragmentManager,
                                       @NonNull ViewModel.Factory<T> factory,
                                       @NonNull String tag,
                                       @Nullable Navigator.Factory navigatorFactory) {

        @SuppressWarnings("unchecked")
        ViewModelHolder<T> viewModelHolder = getViewModelHolder(fragmentManager, factory, tag, navigatorFactory);
        return viewModelHolder.getViewModel();
    }

    private static <T extends ViewModel> ViewModelHolder<T> getViewModelHolder(@NonNull FragmentManager fragmentManager,
                                                                               @NonNull ViewModel.Factory<T> factory,
                                                                               @NonNull String tag,
                                                                               @Nullable Navigator.Factory navigatorFactory){
        @SuppressWarnings("unchecked")
        ViewModelHolder<T> viewModelHolder = (ViewModelHolder<T>) fragmentManager.findFragmentByTag(tag);
        return viewModelHolder == null ? createViewModelHolder(fragmentManager, factory, tag, navigatorFactory) : viewModelHolder;
    }

    private static <T extends ViewModel> ViewModelHolder<T> createViewModelHolder(@NonNull FragmentManager fragmentManager,
                                                                                  @NonNull ViewModel.Factory<T> factory,
                                                                                  @NonNull String tag,
                                                                                  @Nullable Navigator.Factory navigatorFactory){
        Navigator navigator = navigatorFactory != null ? navigatorFactory.createNavigator() : null;
        ViewModelHolder<T> viewModelHolder = ViewModelHolder.newInstance(factory.createViewModel(), navigator);
        fragmentManager.beginTransaction().add(viewModelHolder, tag).commit();
        return viewModelHolder;
    }
}
