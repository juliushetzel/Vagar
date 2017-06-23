package de.juliushetzel.sample.fragments;


import android.databinding.ObservableField;
import android.util.Log;

import de.juliushetzel.vagar.ViewModel;

public class FirstViewModel extends ViewModel{

    public final ObservableField<String> memoryAddress = new ObservableField<>(this.toString());


    @Override
    public void onStart() {
        super.onStart();
        memoryAddress.set(this.toString());
        Log.d(FirstViewModel.class.getSimpleName(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(FirstViewModel.class.getSimpleName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(FirstViewModel.class.getSimpleName(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(FirstViewModel.class.getSimpleName(), "onStop");
    }
}
