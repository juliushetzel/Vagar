package de.juliushetzel.sample;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;


public class MyApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        Log.d("APPLICATION", "Leak Canary installed!");
        // Normal app init code...
    }
}
