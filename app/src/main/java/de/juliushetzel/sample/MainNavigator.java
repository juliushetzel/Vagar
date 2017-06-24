package de.juliushetzel.sample;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import de.juliushetzel.vagar.Navigator;

public class MainNavigator extends Navigator {

    @Override
    public void navigateTo(@Nullable Class<?> clazz, @Nullable Bundle bundle, @Nullable String action) {
        Intent intent = new Intent(getContext(), clazz);
        getContext().startActivity(intent);
    }
}
