package de.juliushetzel.sample;

import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.sample.databinding.ActivityMainBinding;
import de.juliushetzel.vagar.Vagar;
import de.juliushetzel.vagar.annotation.VagarActivity;

@VagarActivity(
        viewModel = MainViewModel.class,
        layout = R.layout.activity_main
)
public class MainActivity extends AppCompatActivity{

    ActivityMainBinding mActivityMainBinding;
    Observable.OnPropertyChangedCallback mNavigationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = Vagar.bind(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationListener = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if(observable == mActivityMainBinding.getViewModel().navigationObservable){
                    Class<?> c = mActivityMainBinding
                            .getViewModel()
                            .navigationObservable
                            .getFirst();

                    Intent intent = new Intent(MainActivity.this, c);
                    startActivity(intent);
                }
            }
        };
        mActivityMainBinding.getViewModel()
                .navigationObservable
                .addOnPropertyChangedCallback(mNavigationListener);
        mActivityMainBinding.activityMemory.setText(this.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityMainBinding.getViewModel()
                .navigationObservable
                .removeOnPropertyChangedCallback(mNavigationListener);
    }
}
