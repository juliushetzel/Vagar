package de.juliushetzel.vagar.deprecated;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;


public final class TagManager {
    static final String EXTRA_TARGET_TAG_RETAIN_ID = "ViewModelBinder:EXTRA_TARGET_TAG_RETAIN_ID";
    private static final long ID_START_VALUE = 1000000;
    private final AtomicLong mNextId;
    private final Map<Long, ActivityTagRetainingLifecycleCallbacks> mActivityTagRetainers;
    private final Map<Long, FragmentTagRetainingLifecycleCallbacks> mFragmentTagRetainers;

    public TagManager() {
        mActivityTagRetainers = new WeakHashMap<>();
        mFragmentTagRetainers = new WeakHashMap<>();
        mNextId = new AtomicLong(ID_START_VALUE);
    }

    public String getTag(@NonNull Activity activity,
                         Bundle savedInstanceState){
        long id = extractId(savedInstanceState);
        return getTagRetainer(id, activity)
                .getTag();
    }

    private long extractId(Bundle savedInstanceState){
        if(savedInstanceState != null){
            return savedInstanceState.getLong(EXTRA_TARGET_TAG_RETAIN_ID);
        }
        return -1;
    }

    private ActivityTagRetainingLifecycleCallbacks getTagRetainer(long id,
                                                                  @NonNull Activity activity){
        ActivityTagRetainingLifecycleCallbacks tagRetainer = mActivityTagRetainers.get(id);
        if(tagRetainer == null){
            tagRetainer = setupNewTagRetainer(activity);
            activity.getApplication().registerActivityLifecycleCallbacks(tagRetainer);
        }
        return tagRetainer;
    }

    private ActivityTagRetainingLifecycleCallbacks setupNewTagRetainer(@NonNull Activity activity){
        String tag = de.juliushetzel.vagar.TagManager.getUniqueTag();
        long id = mNextId.getAndIncrement();
        ActivityTagRetainingLifecycleCallbacks tagRetainer = new ActivityTagRetainingLifecycleCallbacks(
                activity,
                tag,
                id
        );
        mActivityTagRetainers.put(id, tagRetainer);
        return tagRetainer;
    }
}
