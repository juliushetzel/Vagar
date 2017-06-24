package de.juliushetzel.sample.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import de.juliushetzel.sample.navigator.MainNavigator;
import de.juliushetzel.sample.R;
import de.juliushetzel.sample.databinding.ActivityMainBinding;
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
    public static final String SHARED_PREFERENCES_TAG = "PREF:Example";
    public static final String PREF_KEY_LAST_OPEN_SCREEN = "PREF:Example";
    public static final String SCREEN_COMPLETED = "completed";
    public static final String SCREEN_UNCOMPLETED = "uncompleted";

    ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = Vagar.bind(this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getSharedPreferences(SHARED_PREFERENCES_TAG, MODE_PRIVATE)
                .getString(PREF_KEY_LAST_OPEN_SCREEN, SCREEN_COMPLETED)
                .equals(SCREEN_COMPLETED)){
            mActivityMainBinding.getViewModel().showCompletedTasks();
        }else{
            mActivityMainBinding.getViewModel().showUncompletedTasks();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        String currentScreen = mActivityMainBinding.getViewModel().showingCompletedTasks.get() ? SCREEN_COMPLETED : SCREEN_UNCOMPLETED;
        getSharedPreferences(SHARED_PREFERENCES_TAG, MODE_PRIVATE)
                .edit()
                .putString(PREF_KEY_LAST_OPEN_SCREEN, currentScreen)
                .apply();
    }

    @Override
    public MainActivityViewModel createViewModel() {

        return new MainActivityViewModel();
    }
}
