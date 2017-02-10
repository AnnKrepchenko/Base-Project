package com.krepchenko.base_proj.utils;

import android.content.SharedPreferences;

public class PreferencesUtils {

    protected SharedPreferences sharedPreferences;

    protected final String PROP_AUTHORIZATION_TOKEN = "authorization_token";
    protected final String PROP_PHONE_NUMBER = "phone";



    protected String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    protected void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    protected int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    protected void setInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    protected boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    protected void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

}
