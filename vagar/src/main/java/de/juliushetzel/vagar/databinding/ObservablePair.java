package de.juliushetzel.vagar.databinding;


import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

public class ObservablePair<F, S> extends ObservableField<Pair<F, S>> {

    public ObservablePair(){
        super();
    }

    public ObservablePair(@Nullable Pair<F,S> pair){
        super(pair);
    }

    public ObservablePair(@Nullable F first, @Nullable S second){
        this(new Pair<>(first, second));
    }

    public void set(@Nullable F first, @Nullable S second){
        set(new Pair<>(first, second));
    }

    public F getFirst(){
        return get().first;
    }

    public S getSecond(){
        return get().second;
    }
}
