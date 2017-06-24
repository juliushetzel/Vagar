package jhetzel.vagar.util;


public final class Conditions {
    public static Condition plain(final boolean isFullFilled){
        return new Condition() {
            @Override
            public boolean isFullFilled() {
                return isFullFilled;
            }
        };
    }
}
