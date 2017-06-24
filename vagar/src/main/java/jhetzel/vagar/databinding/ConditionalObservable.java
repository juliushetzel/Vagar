package jhetzel.vagar.databinding;


import android.databinding.BaseObservable;

import jhetzel.vagar.util.Condition;

public class ConditionalObservable extends BaseObservable{
    private Condition mCondition;

    public ConditionalObservable(Condition condition){
        mCondition = condition;
    }

    private void changeCondition(Condition condition){
        mCondition = condition;
    }

    @Override
    public void notifyChange() {
        if(mCondition.isFullFilled()) {
            super.notifyChange();
        }
    }

    @Override
    public void notifyPropertyChanged(int fieldId) {
        if(mCondition.isFullFilled()) {
            super.notifyPropertyChanged(fieldId);
        }
    }
}
