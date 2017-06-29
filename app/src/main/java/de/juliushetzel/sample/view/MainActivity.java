package de.juliushetzel.sample.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.databinding.ActivityMainBinding;
import de.juliushetzel.sample.navigation.MainNavigator;
import de.juliushetzel.sample.viewmodel.MainActivityViewModel;
import jhetzel.vagar.Vagar;
import jhetzel.vagar.ViewModel;
import jhetzel.vagar.annotation.Assemble;

@Assemble(
        viewModel = MainActivityViewModel.class,
        layout = R.layout.activity_main,
        navigator = MainNavigator.class
)
public class MainActivity extends FragmentActivity implements ViewModel.Factory<MainActivityViewModel>{

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = Vagar.bind(this, this);
    }

    @Override
    public MainActivityViewModel createViewModel() {
        return new MainActivityViewModel();
    }
}
