package de.juliushetzel.vagar.databinding;


public abstract class Condition{

    abstract boolean isFullFilled();

    public static Condition returns(final boolean isFullFilled){
        return new Condition() {
            @Override
            public boolean isFullFilled() {
                return isFullFilled;
            }
        };
    }
}

