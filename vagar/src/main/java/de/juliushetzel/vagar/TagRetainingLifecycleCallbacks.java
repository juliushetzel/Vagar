package de.juliushetzel.vagar;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

abstract class TagRetainingLifecycleCallbacks<T>{
    private final String mTag;
    private WeakReference<T> mTargetReference;

    TagRetainingLifecycleCallbacks(@NonNull T target,
                                          @NonNull String tag) {
        mTargetReference = new WeakReference<>(target);
        mTag = tag;
    }

    protected final void onCreate(T target, Bundle savedInstanceState){
        if(savedInstanceState != null &&
                savedInstanceState.getString(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER) == mTag){
            mTargetReference = new WeakReference<>(target);
        }
    }

    protected final void onSaveInstanceState(T target, Bundle outState){
        if(mTargetReference.get() != null &&
                mTargetReference.get() == target){
            outState.putString(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER, mTag);
            mTargetReference.clear();
        }
    }

    protected final void onDestroy(T target){
        if(mTargetReference.get() == target){
            mTargetReference.clear();
        }
    }
}
