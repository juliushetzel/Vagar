package de.juliushetzel.sample;


import android.content.Intent;
import android.os.Bundle;

import de.juliushetzel.vagar.Navigator;

public class MainNavigator extends Navigator<MainActivity> {

    @Override
    public void navigateTo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clazz);
        getContext().startActivity(intent);
    }
}
