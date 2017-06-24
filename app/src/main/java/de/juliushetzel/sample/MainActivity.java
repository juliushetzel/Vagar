package de.juliushetzel.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.sample.databinding.ActivityMainBinding;
import de.juliushetzel.vagar.Navigator;
import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.ViewModelProvider;
import de.juliushetzel.vagar.annotation.Assemble;

@Assemble(
        viewModel = MainViewModel.class,
        layout = R.layout.activity_main,
        navigator = MainNavigator.class
)
public class MainActivity extends AppCompatActivity implements ViewModel.Factory<MainViewModel>, Navigator.Factory{

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel viewModel = ViewModelProvider.get(getFragmentManager(), this, "123123123", this);
        mActivityMainBinding.setViewModel(viewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityMainBinding.activityMemory.setText(this.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public MainViewModel createViewModel() {
        return new MainViewModel();
    }

    @Override
    public Navigator createNavigator() {
        return new MainNavigator();
    }
}
