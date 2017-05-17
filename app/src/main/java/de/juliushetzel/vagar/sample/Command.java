package de.juliushetzel.vagar.sample;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

public class Command<P> extends BaseObservable{
    private final Function mAction;
    private final Function mCanExecuteHandler;

    public Command(Function action){
        this(action, null);
    }

    public Command(@NonNull Function action,
                   Function canExecuteHandler) {
        mAction = action;
        mCanExecuteHandler = canExecuteHandler;
    }

    public void execute(){
        mAction.apply();
        notifyChange();
    }

    public boolean canExecute(){
        return mCanExecuteHandler.apply();
    }

    public interface Function{

        boolean apply();
    }
}
