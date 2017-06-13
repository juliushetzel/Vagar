package de.juliushetzel.vagar;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

abstract class TagRetainer<T>{
    private final String mTag;
    private WeakReference<T> mTargetReference;

    TagRetainer(@NonNull T target,
                @NonNull String tag) {
        mTargetReference = new WeakReference<>(target);
        mTag = tag;
    }

    final void setReference(T target, Bundle savedInstanceState){
        if(containsTag(savedInstanceState)){
            mTargetReference = new WeakReference<>(target);
        }
    }

    final void saveTag(T target, Bundle outState){
        if(isTarget(target)){
            outState.putString(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER, mTag);
        }
    }

    final void clearReference(T target){
        if(isTarget(target)){
            mTargetReference.clear();
        }

        // GLOBALEN TAG RETAINER!!!!! singleton
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
