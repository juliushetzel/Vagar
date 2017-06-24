package de.juliushetzel.sample.second;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.sample.R;
import de.juliushetzel.sample.databinding.ActivityOtherBinding;
import jhetzel.vagar.Vagar;
import jhetzel.vagar.ViewModel;
import jhetzel.vagar.annotation.Assemble;

@Assemble(
        viewModel = OtherViewModel.class,
        layout = R.layout.activity_other,
        viewModelTag = "exampleTag"
)
public class OtherActivity extends AppCompatActivity implements ViewModel.Factory<OtherViewModel>{

    private ActivityOtherBinding mViewBinding;
    private Observable.OnPropertyChangedCallback mNavigationListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewBinding = Vagar.bind(this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mNavigationListener = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if(observable == mViewBinding.getExampleTag().navigationObservable){

                    OtherActivity.this.finish();
                }
            }
        };
        mViewBinding.getExampleTag().navigationObservable.addOnPropertyChangedCallback(mNavigationListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewBinding.getExampleTag().navigationObservable.removeOnPropertyChangedCallback(mNavigationListener);
    }

    @Override
    public OtherViewModel createViewModel() {
        return new OtherViewModel();
    }
}
