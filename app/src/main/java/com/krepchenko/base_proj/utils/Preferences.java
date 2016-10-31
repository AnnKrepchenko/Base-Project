package com.krepchenko.base_proj.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.krepchenko.base_proj.BuildConfig;
import com.krepchenko.base_proj.core.App;
import com.krepchenko.base_proj.net.core.GsonWrapper;

import java.util.Arrays;
import java.util.List;

public class Preferences {

    private static Preferences preferences = null;
    private SharedPreferences sharedPreferences;
    private Gson gson = GsonWrapper.getGson();

    private final String PROP_AUTHORIZATION_TOKEN = "authorization_token";
    private final String PROP_USERNAME = "username";
    private final String PROP_NAME = "name";

    public static Preferences getInstance() {
        if (preferences == null) {
            preferences = new Preferences();
        }
        return preferences;
    }

    public Preferences() {
        sharedPreferences = App.getInstance().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getAuthorizationToken() {
        return getString(PROP_AUTHORIZATION_TOKEN, null);
    }

    public void setAuthorizationToken(String token) {
        setString(PROP_AUTHORIZATION_TOKEN, token);
    }

    public String getUsername() {
        return getString(PROP_USERNAME, null);
    }

    public void setUsername(String token) {
        setString(PROP_USERNAME, token);
    }

    public String getName() {
        return getString(PROP_NAME, null);
    }

    public void setName(String token) {
        setString(PROP_NAME, token);
    }

}