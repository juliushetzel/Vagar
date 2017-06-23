package de.juliushetzel.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.sample.databinding.ActivityMainBinding;
import de.juliushetzel.vagar.ViewModel;
import de.juliushetzel.vagar.annotation.Vagar;

@Vagar(
        viewModel = MainViewModel.class,
        layout = R.layout.activity_main
)
public class MainActivity extends AppCompatActivity implements ViewModel.Factory<MainViewModel>{

    ActivityMainBinding mActivityMainBinding;
    MainNavigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = de.juliushetzel.vagar.Vagar.bind(this, this);
        mNavigator = new MainNavigator();
        mActivityMainBinding.getViewModel().navigation.addOnPropertyChangedCallback(mNavigator);
        mActivityMainBinding.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Fragment getItem(int position) {
                //return FirstFragment.newInstance();
                return null;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigator.attachActivity(this);
        mActivityMainBinding.activityMemory.setText(this.toString());
        mActivityMainBinding.getViewModel().navigation.removeOnPropertyChangedCallback(mNavigator);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mNavigator.detachActivity();
    }

    @Override
    public MainViewModel createViewModel() {
        return new MainViewModel();
    }
}
