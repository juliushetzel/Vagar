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

    private final BundleTagInjector<Activity> mActivityBundleTagInjector = new ActivityBundleTagInjector(EXTRA_TAG_VIEW_MODEL_HOLDER);
    //private final BundleTagInjector<Fragment> mFragmentBundleTagInjector = new BundleTagInjector<>(EXTRA_TAG_VIEW_MODEL_HOLDER);

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
        String tag = extractTag(savedInstanceState);
        removeTag(savedInstanceState);
        if(tag == null){
            tag = getUniqueTag();
            mActivityBundleTagInjector.registerForInjection(activity, tag);
        }
        return tag;
    }

    /* for Fragment */

    String getTag(@NonNull Fragment fragment,
                         Bundle savedInstanceState){
        String tag = extractTag(savedInstanceState);
        removeTag(savedInstanceState);
        if(tag == null){
            tag = getUniqueTag();
            addToTagRetainer(fragment, tag);
        }
        return tag;
    }

    private void addToTagRetainer(@NonNull Fragment fragment,
                                  @NonNull String newTag){
        /*FragmentTagRetainerOLD tagRetainingLifecycleCallbacks = new FragmentTagRetainerOLD(
                fragment,
                newTag
        );
        fragment.getFragmentManager().registerFragmentLifecycleCallbacks(tagRetainingLifecycleCallbacks.asCallbacks(), false);*/
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
        if(savedInstanceState != null) {
            savedInstanceState.remove(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER);
        }
    }
}
