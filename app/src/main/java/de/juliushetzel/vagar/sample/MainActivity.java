package de.juliushetzel.vagar.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.annotation.VagarActivity;

@VagarActivity(
        viewModel = MainViewModel.class,
        layout = R.layout.activity_main
)
public class MainActivity extends AppCompatActivity implements ViewModel.Factory<MainViewModel>{

    //ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mActivityMainBinding = Vagar.bind(this, savedInstanceState);
    }



    @Override
    public MainViewModel createViewModel() {
        return new MainViewModel();
    }
}
