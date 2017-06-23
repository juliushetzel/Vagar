package de.juliushetzel.vagar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicLong;

public final class TagManager {
    static final String EXTRA_TAG_VIEW_MODEL_HOLDER = "ViewModelBinder:internal:EXTRA_TAG_VIEW_MODEL_HOLDER";
    private static final String TAG_PREFIX = "ViewModelBinder:internal:view_model_holder_";
    private static final AtomicLong NEXT_TAG = new AtomicLong(0);

    private final BundleTagInjector<Activity> mActivityBundleTagInjector = new ActivityBundleTagInjector(EXTRA_TAG_VIEW_MODEL_HOLDER);

    /**
     * @return a unique String tag
     */
    public static String getUniqueTag(){
        return new StringBuilder(TAG_PREFIX)
                .append(NEXT_TAG.getAndIncrement())
                .toString();
    }

    TagManager(){}

    /* for Activity */

    /**
     * Returns the matching tag for the viewModel or creates a new
     * ActivityLifecycleCallbacks subclass which will addTagReferencePair the tag,
     * and registers it
     * @param activity the activity which the viewModel should be bound to
     * @param savedInstanceState the SavedInstanceState of the activity
     *                           which the viewModel should be bound to
     * @return the matching tag
     */
    String getTag(@NonNull Activity activity,
                         Bundle savedInstanceState){
        String tag = extractTag(EXTRA_TAG_VIEW_MODEL_HOLDER, savedInstanceState);
        removeTag(EXTRA_TAG_VIEW_MODEL_HOLDER, savedInstanceState);
        if(tag == null){
            tag = getUniqueTag();
            mActivityBundleTagInjector.registerForInjection(activity, tag);
        }
        return tag;
    }

    /* for All */

    static String extractTag(String key, Bundle savedInstanceState){
        String tag = null;
        if(savedInstanceState != null){
            tag = savedInstanceState.getString(key);
        }
        return tag;
    }

    private void removeTag(String key, Bundle savedInstanceState){
        if(savedInstanceState != null) {
            savedInstanceState.remove(key);
        }
    }
}
