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

    final void onCreate(T target, Bundle savedInstanceState){
        if(containsTag(savedInstanceState)){
            mTargetReference = new WeakReference<>(target);
        }
    }

    final void onSaveInstanceState(T target, Bundle outState){
        if(isTarget(target)){
            outState.putString(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER, mTag);
            mTargetReference.clear();
        }
    }

    final void onDestroy(T target){
        if(mTargetReference.get() == target){
            mTargetReference.clear();
        }
    }

    private boolean containsTag(Bundle savedInstanceState){
        return savedInstanceState != null &&
                mTag.equals(savedInstanceState.getString(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER));
    }

    private boolean isTarget(T target){
        return mTargetReference.get() != null &&
                mTargetReference.get() == target;
    }
}
