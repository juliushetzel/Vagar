package de.juliushetzel.vagar.sample;

import android.databinding.ObservableField;

import de.juliushetzel.vagar.ViewModel;

public class MainViewModel implements ViewModel {

    private int clicks = 0;

    private boolean mActive;

    public final Command test = new Command(new Command.Function() {
        @Override
        public boolean apply() {
            text.set("CLICK" + clicks++);
            return false;
        }
    }, new Command.Function() {
        @Override
        public boolean apply() {
            return mActive;
        }
    });

    public void doSth(){
        mActive = !mActive;
        test.notifyChange();
    }

    public final ObservableField<String> text = new ObservableField<>("NEU");

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
