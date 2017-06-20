package de.juliushetzel.sample;

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
    MainNavigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = Vagar.bind(this, savedInstanceState);
        mNavigator = new MainNavigator();
        mActivityMainBinding.getViewModel().setNavigator(mNavigator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigator.attachActivity(this);
        mActivityMainBinding.activityMemory.setText(this.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mNavigator.detachActivity();
    }
}
