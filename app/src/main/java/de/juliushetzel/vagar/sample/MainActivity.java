package de.juliushetzel.vagar.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.ViewModelBinder;
import de.juliushetzel.vagar.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements ViewModel.Factory<MainViewModel>{

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainViewModel viewModel = ViewModelBinder.bind(this, this, savedInstanceState);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityMainBinding.setViewModel(viewModel);
    }



    @Override
    public MainViewModel createViewModel() {
        return new MainViewModel();
    }
}
