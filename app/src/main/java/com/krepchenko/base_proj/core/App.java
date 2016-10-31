package com.krepchenko.base_proj.core;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Ann
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Stetho.initializeWithDefaults(this);
    }

    public static App getInstance() {
        return instance;
    }
}
