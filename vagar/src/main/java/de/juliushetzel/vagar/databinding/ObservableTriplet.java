package de.juliushetzel.vagar.databinding;


import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.juliushetzel.vagar.util.Triplet;

public final class ObservableTriplet<F, S, T> extends ObservableField<Triplet<F,S,T>> {

    public ObservableTriplet(F first, S second, T triple) {
        super(new Triplet<>(first, second, triple));
    }

    ObservableTriplet(){super();}

    public void set(@Nullable F first, @Nullable S second, @Nullable T third){
        set(new Triplet<>(first, second, third));
    }

    @Override
    public void set(@NonNull Triplet<F, S, T> value) {
        super.set(value);
    }

    public F getFirst() {
        return get().getFirst();
    }

    public S getSecond() {
        return get().getSecond();
    }

    public T getThird() {
        return get().getThird();
    }
}
