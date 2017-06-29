package jhetzel.vagar;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public final class ViewModelProvider {

    /* ----------------------------- for Activity ----------------------------- */

    /**
     * Entry Point Method. This Method will retain a {@link ViewModel} subclass instance
     * of Type {@param <T>}
     *
     * @param activity the activity to which layout the {@link ViewModel} should be
     *                 bound to.
     * @param factory a {@link ViewModel.Factory} that provides an instance of {@link ViewModel}
     * @param tag the tag under which the {@link ViewModel} will be retained
     * @param <T> a subclass of {@link ViewModel}
     * @return the retained {@link ViewModel} instance provided by the {@param factory}
     */
    public static <T extends ViewModel> T get(@NonNull Activity activity,
                                              @NonNull ViewModel.Factory<T> factory,
                                              @NonNull String tag){
        return get(activity, factory, tag, null);
    }

    /**
     * Entry Point Method. This Method will retain a {@link ViewModel} subclass instance
     * of Type {@param <T>} and bind a {@link Navigator} provided by the {@param navigatorFactory}
     * to it.
     *
     * @param activity the {@link Activity} to which layout the {@link ViewModel} should be
     *                 bound to.
     * @param factory a {@link ViewModel.Factory} that provides an instance of {@link ViewModel}
     * @param tag the tag under which the {@link ViewModel} will be retained
     * @param navigatorFactory a {@link Navigator.Factory} that provides an instance of
     *                         the {@link Navigator}. This instance will react to
     *                         {@link ViewModel#navigateTo(NavigationEvent)}
     * @param <T> a subclass of {@link ViewModel}
     * @return the retained {@link ViewModel} instance provided by the {@param factory}
     */
    public static <T extends ViewModel> T get(@NonNull Activity activity,
                                               @NonNull ViewModel.Factory<T> factory,
                                               @NonNull String tag,
                                               @Nullable Navigator.Factory navigatorFactory) {
        return get(activity.getFragmentManager(), factory, tag, navigatorFactory);
    }


    /* ----------------------------- for Fragment ----------------------------- */

    /**
     * Entry Point Method. This Method will retain a {@link ViewModel} subclass instance
     * of Type {@param <T>}
     *
     * @param fragment the {@link Fragment} to which layout the {@link ViewModel} should be
     *                 bound to.
     * @param factory a {@link ViewModel.Factory} that provides an instance of {@link ViewModel}
     * @param tag the tag under which the {@link ViewModel} will be retained
     * @param <T> a subclass of {@link ViewModel}
     * @return the retained {@link ViewModel} instance provided by the {@param factory}
     */
    public static <T extends ViewModel> T get(@NonNull Fragment fragment,
                                              @NonNull ViewModel.Factory<T> factory,
                                              @NonNull String tag){
        return get(fragment, factory, tag, null);
    }

    /**
     * Entry Point Method. This Method will retain a {@link ViewModel} subclass instance
     * of Type {@param <T>} and bind a {@link Navigator} provided by the {@param navigatorFactory}
     * to it.
     *
     * @param fragment the {@link Fragment} to which layout the {@link ViewModel} should be
     *                 bound to.
     * @param factory a {@link ViewModel.Factory} that provides an instance of {@link ViewModel}
     * @param tag the tag under which the {@link ViewModel} will be retained
     * @param navigatorFactory a {@link Navigator.Factory} that provides an instance of
     *                         the {@link Navigator}. This instance will react to
     *                         {@link ViewModel#navigateTo(NavigationEvent)}
     * @param <T> a subclass of {@link ViewModel}
     * @return the retained {@link ViewModel} instance provided by the {@param factory}
     */
    public static <T extends ViewModel> T get(@NonNull Fragment fragment,
                                               @NonNull ViewModel.Factory<T> factory,
                                               @NonNull String tag,
                                               @Nullable Navigator.Factory navigatorFactory) {
        return get(fragment.getActivity().getFragmentManager(), factory, tag, navigatorFactory);
    }

    /* ----------------------------- internal ----------------------------- */

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
    private static <T extends ViewModel> T get(@NonNull FragmentManager fragmentManager,
                                       @NonNull ViewModel.Factory<T> factory,
                                       @NonNull String tag,
                                       @Nullable Navigator.Factory navigatorFactory) {
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
