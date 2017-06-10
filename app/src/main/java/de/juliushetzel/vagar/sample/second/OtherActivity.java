package de.juliushetzel.vagar.sample.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import de.juliushetzel.vagar.annotation.VagarActivity;
import de.juliushetzel.vagar.sample.R;

@VagarActivity(
        viewModel = OtherViewModel.class,
        layout = R.layout.activity_other,
        viewModelTag = "exampleTag"
)
public class OtherActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityOtherBinding binding = Vagar.bind(this, savedInstanceState);
    }
}
