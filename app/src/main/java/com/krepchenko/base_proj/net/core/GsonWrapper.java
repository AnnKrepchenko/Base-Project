package com.krepchenko.base_proj.net.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonWrapper {
    private static Gson gson;

    protected GsonWrapper() {
    }

    public static synchronized Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .create();
        }
        return gson;
    }
}