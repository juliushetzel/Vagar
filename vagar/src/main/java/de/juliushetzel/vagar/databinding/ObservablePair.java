package de.juliushetzel.vagar.databinding;


import android.databinding.BaseObservable;
import android.support.v4.util.Pair;

public class ObservablePair<F, S> extends BaseObservable {
    private Pair<F, S> mPair;

    /**
     * Wraps the given objects and creates an observable object
     *
     * @param first The first value of the pair to be wrapped as an observable.
     * @param second The second value of the pair to be wrapped as an observable.
     */
    public ObservablePair(F first, S second) {
        this(new Pair<>(first, second));
    }

    /**
     * Wraps the given object and creates an observable object
     *
     * @param pair The pair to be wrapped as an observable.
     */
    public ObservablePair(Pair<F, S> pair) {
        mPair = pair;
    }

    /**
     * Creates an empty observable object
     */
    public ObservablePair(){
        super();
    }

    /**
     * @return the stored pair.
     */
    public Pair<F, S> get() {
        return mPair;
    }

    /**
     * @return the first value of the stored pair.
     */
    public F getFirst(){
        return get().first;
    }

    /**
     * @return the second value of the stored pair.
     */
    public S getSecond(){
        return get().second;
    }

    /**
     * Set the stored pair.
     */
    public void set(Pair<F, S> pair) {
        if (pair != mPair) {
            mPair = pair;
            notifyChange();
        }
    }

    /**
     * Set the stored pair.
     *
     * @param first The first value of the pair.
     * @param second The second value of the pair.
     */
    public void set(F first, S second){
        set(new Pair<>(first, second));
    }
}
