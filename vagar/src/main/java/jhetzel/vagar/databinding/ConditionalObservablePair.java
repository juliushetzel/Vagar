package jhetzel.vagar.databinding;


import android.support.v4.util.Pair;

import jhetzel.vagar.util.Condition;

public class ConditionalObservablePair<F, S> extends ConditionalObservable {

    static final long serialVersionUID = 1L;
    private Pair<F, S> mPair;

    /**
     * Wraps the given objects and creates an observable object
     *
     * @param first The first value of the pair to be wrapped as an observable.
     * @param second The second value of the pair to be wrapped as an observable.
     */
    public ConditionalObservablePair(Condition condition, F first, S second) {
        this(condition, new Pair<>(first, second));
    }

    /**
     * Wraps the given object and creates an observable object
     *
     * @param pair The pair to be wrapped as an observable.
     */
    public ConditionalObservablePair(Condition condition, Pair<F, S> pair) {
        super(condition);
        mPair = pair;
    }

    /**
     * Creates an empty observable object
     */
    public ConditionalObservablePair(Condition condition){
        super(condition);
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
