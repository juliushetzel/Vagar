package de.juliushetzel.sample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import de.juliushetzel.vagar.Navigator;

public class MainNavigator extends Navigator<MainActivity> {

    @Override
    public void navigateTo(Class<? extends Activity> activity, Bundle bundle) {
        Intent intent = new Intent(getContext(), activity);
        getContext().startActivity(intent);
    }
}
