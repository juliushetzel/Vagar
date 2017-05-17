package de.juliushetzel.vagar.deprecated;

import android.os.Bundle;

import java.lang.ref.WeakReference;

public abstract class TagRetainingLifecycleCallbacks<T>{
    private final String mTag;
    private final long mId;
    private WeakReference<T> mTargetReference;

    public TagRetainingLifecycleCallbacks(T target,
                                             String tag,
                                             long id) {
        mTargetReference = new WeakReference<>(target);
        mTag = tag;
        mId = id;
    }


    public String getTag(){
        return mTag;
    }

    protected final void onCreate(T target, Bundle savedInstanceState){
        if(savedInstanceState != null &&
                savedInstanceState.getLong(TagManager.EXTRA_TARGET_TAG_RETAIN_ID) == mId){
            mTargetReference = new WeakReference<>(target);
        }
        savedInstanceState.remove(TagManager.EXTRA_TARGET_TAG_RETAIN_ID);
    }

    protected final void onSaveInstanceState(T target, Bundle outState){
        if(mTargetReference.get() != null &&
                mTargetReference.get() == target){
            outState.putLong(TagManager.EXTRA_TARGET_TAG_RETAIN_ID, mId);
            mTargetReference.clear();
        }
    }

    protected final void onDestroy(T target){
        if(mTargetReference.get() == target){
            mTargetReference.clear();
        }
    }
}
