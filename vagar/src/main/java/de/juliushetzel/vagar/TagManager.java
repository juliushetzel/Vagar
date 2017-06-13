package de.juliushetzel.vagar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.concurrent.atomic.AtomicLong;

public final class TagManager {
    static final String EXTRA_TAG_VIEW_MODEL_HOLDER = "ViewModelBinder:internal:EXTRA_TAG_VIEW_MODEL_HOLDER";
    private static final String TAG_PREFIX = "ViewModelBinder:internal:view_model_holder_";
    private static final AtomicLong NEXT_TAG = new AtomicLong(0);

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
     * ActivityLifecycleCallbacks subclass which will retain the tag,
     * and registers it
     * @param activity the activity which the viewModel should be bound to
     * @param savedInstanceState the SavedInstanceState of the activity
     *                           which the viewModel should be bound to
     * @return the matching tag
     */
    String getTag(@NonNull Activity activity,
                         Bundle savedInstanceState){
        String tag = extractTag(savedInstanceState);
        removeTag(savedInstanceState);
        if(tag == null){
            tag = getUniqueTag();
            setupNewTagRetainer(activity, tag);
        }
        return tag;
    }

    private void setupNewTagRetainer(@NonNull Activity activity,
                                    @NonNull String newTag){
        ActivityTagRetainer tagRetainingLifecycleCallbacks = new ActivityTagRetainer(
                activity,
                newTag
        );
        activity.getApplication().registerActivityLifecycleCallbacks(tagRetainingLifecycleCallbacks);
    }

    /* for Fragment */

    String getTag(@NonNull Fragment fragment,
                         Bundle savedInstanceState){
        String tag = extractTag(savedInstanceState);
        removeTag(savedInstanceState);
        if(tag == null){
            tag = getUniqueTag();
            setupNewTagRetainer(fragment, tag);
        }
        return tag;
    }

    private void setupNewTagRetainer(@NonNull Fragment fragment,
                                     @NonNull String newTag){
        FragmentTagRetainer tagRetainingLifecycleCallbacks = new FragmentTagRetainer(
                fragment,
                newTag
        );
        fragment.getFragmentManager().registerFragmentLifecycleCallbacks(tagRetainingLifecycleCallbacks.asCallbacks(), false);
    }

    /* for All */

    static String extractTag(Bundle savedInstanceState){
        String tag = null;
        if(savedInstanceState != null){
            tag = savedInstanceState.getString(EXTRA_TAG_VIEW_MODEL_HOLDER);
        }
        return tag;
    }

    private void removeTag(Bundle savedInstanceState){
        savedInstanceState.remove(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER);
    }
}
