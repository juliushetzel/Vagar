package jhetzel.vagar;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Holds the Values needed by the {@link Navigator} to execute a
 * Navigation.
 *
 */
public final class NavigationEvent {
    private String mAction;
    private Uri mUri;
    private Class<?> mClass;
    private Bundle mBundle;

    public NavigationEvent(){
    }

    /**
     * Usually used so provide {@link android.content.Intent} actions.
     *
     * @param action an action constant like {@link android.content.Intent#ACTION_ANSWER}
     * @return the current {@link NavigationEvent} instance
     */
    public NavigationEvent setAction(String action) {
        mAction = action;
        return this;
    }

    /**
     * @param uri {@link Uri} to provide for the {@link Navigator} to build
     *                       an {@link android.content.Intent}
     * @return the current {@link NavigationEvent} instance
     */
    public NavigationEvent setUri(Uri uri) {
        mUri = uri;
        return this;
    }

    /**
     * Used to provide a {@link Class} to navigate to. This should be
     * an {@link android.app.Activity} to generate an {@link android.content.Intent}
     * or a {@link android.app.Fragment} class object to tell the corresponding
     * {@link android.app.Activity} within the {@link Navigator} to show an instance
     * of that {@link android.app.Fragment} class.
     *
     * @param cls should be a {@link Class} object of type {@link android.app.Fragment}
     *            or {@link android.app.Activity}
     * @return the current {@link NavigationEvent} instance
     */
    public NavigationEvent setComponentClass(Class<?> cls) {
        mClass = cls;
        return this;
    }

    /**
     * Used to forward information to a {@link android.app.Fragment} or
     * {@link android.app.Activity}
     *
     * @param bundle a {@link Bundle} to hold the information that needs
     *               to be forwarded
     * @return the current {@link NavigationEvent} instance
     */
    public NavigationEvent setBundle(Bundle bundle) {
        mBundle = bundle;
        return this;
    }

    /**
     * @return the {@link String} provided in {@link NavigationEvent#setAction(String)}
     */
    @Nullable
    public String getAction() {
        return mAction;
    }

    /**
     * @return the {@link Uri} provided in {@link NavigationEvent#setUri(Uri)}
     */
    @Nullable
    public Uri getUri() {
        return mUri;
    }

    /**
     * @return the {@link Class} provided in {@link NavigationEvent#setComponentClass(Class)}
     */
    @Nullable
    public Class<?> getComponentClass() {
        return mClass;
    }

    /**
     * @return the {@link Bundle} provided in {@link NavigationEvent#setBundle(Bundle)}
     */
    @Nullable
    public Bundle getBundle() {
        return mBundle;
    }
}
