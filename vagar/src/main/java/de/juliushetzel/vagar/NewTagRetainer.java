package de.juliushetzel.vagar;


import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class NewTagRetainer<T>{
    private final Map<String, WeakReference<T>> mTargetTagMap;

    NewTagRetainer() {
        mTargetTagMap = new HashMap<>();
    }

    /**
     * add a new WeakReference to followTarget
     * @param target
     * @param tag
     */
    void addTargetToFollow(T target, String tag){
        mTargetTagMap.put(tag, new WeakReference<T>(target));
    }


    void followTarget(T target, Bundle savedInstanceState){
        String tag = TagManager.extractTag(savedInstanceState);
        if(mTargetTagMap.containsKey(tag)){
            mTargetTagMap.put(tag, new WeakReference<T>(target));
        }
    }

    void saveTag(T target, Bundle outState){
        String tag = getTagFor(target);
        outState.putString(TagManager.EXTRA_TAG_VIEW_MODEL_HOLDER, tag);
    }

    private String getTagFor(T target){
        for(Map.Entry<String, WeakReference<T>> entry : mTargetTagMap.entrySet()){
            if(target != null &&
                    target == entry.getValue().get()){
                return entry.getKey();
            }
        }
        return null;
    }

    private void clearReference(T target){
        String tag = getTagFor(target);
        if(tag != null && mTargetTagMap.get(tag) != null){
            mTargetTagMap.get(tag).clear();
        }
    }

}
