package de.juliushetzel.vagar;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

final class Conditions{

    static void checkRetainInstance(@NonNull Fragment fragment){
        if(!fragment.getRetainInstance()){
            throw new IllegalArgumentException("The given fragment needs to bew retained. Please call setRetainInstance(true) within the fragment.");
        }
    }
}
