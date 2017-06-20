package de.juliushetzel.vagar.databinding;


import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.List;

interface CallbackCache {

    void cache(BaseObservable observable, Observable.OnPropertyChangedCallback callback);

    void release();

    class CallbackCacheImplementation implements CallbackCache{
        List<Pair<BaseObservable, Observable.OnPropertyChangedCallback>> mCallbackMap;

        CallbackCacheImplementation() {
            mCallbackMap = new ArrayList<>();
        }

        @Override
        public void cache(BaseObservable observable, Observable.OnPropertyChangedCallback callback) {
            mCallbackMap.add(new Pair<>(observable, callback));
        }

        @Override
        public void release() {
            for(Pair<BaseObservable, Observable.OnPropertyChangedCallback> entry : mCallbackMap){
                entry.first.removeOnPropertyChangedCallback(entry.second);
            }
            mCallbackMap.clear();
        }
    }
}
