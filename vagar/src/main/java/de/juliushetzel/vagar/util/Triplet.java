package de.juliushetzel.vagar.util;

/**
 * Container to ease passing around a triplet of three objects.
 */
public final class Triplet<F, S, T> {
    private final F mFirst;
    private final S mSecond;
    private final T mThird;

    /**
     * Constructor for a Triplet.
     *
     * @param first the first object in the triplet
     * @param second the second object in the triplet
     * @param third the third object in the triplet
     */
    public Triplet(F first, S second, T third) {
        mFirst = first;
        mSecond = second;
        mThird = third;
    }

    /**
     * @return the first value of the triplet
     */
    public F getFirst() {
        return mFirst;
    }

    /**
     * @return the second value of the triplet
     */
    public S getSecond() {
        return mSecond;
    }

    /**
     * @return the third value of the triplet
     */
    public T getThird() {
        return mThird;
    }
}
