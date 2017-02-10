package com.krepchenko.base_proj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.krepchenko.base_proj.BuildConfig;
import com.krepchenko.base_proj.core.App;
import com.krepchenko.base_proj.net.core.GsonWrapper;

import java.util.Arrays;
import java.util.List;

public class Preferences  extends PreferencesUtils {

    private static Preferences preferences = null;
    private Gson gson = GsonWrapper.getGson();

    public static Preferences getInstance() {
        if (preferences == null) {
            preferences = new Preferences();
        }
        return preferences;
    }

    public Preferences() {
        sharedPreferences = App.getInstance().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }

    public String getAuthorizationToken() {
        return TokenUtils.decryptToken(getString(PROP_AUTHORIZATION_TOKEN, null));
    }

    public void setAuthorizationToken(String token) {
        setString(PROP_AUTHORIZATION_TOKEN, TokenUtils.encryptToken(token));
    }

}