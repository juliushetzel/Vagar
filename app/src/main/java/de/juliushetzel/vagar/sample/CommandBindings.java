package de.juliushetzel.vagar.sample;

import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.view.View;
import android.widget.Button;

public class CommandBindings {

    @BindingAdapter("bind:onClick")
    public static <T> void setCommand(final Button button, final Command<T> command){
        button.setEnabled(command.canExecute());
        command.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                button.setEnabled(((Command) observable).canExecute());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                command.execute();
            }
        });
    }
}
