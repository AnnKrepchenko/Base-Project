package com.krepchenko.base_proj.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.krepchenko.base_proj.R;


public class Launcher {

    public static void replaceFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, String tag) {
        try {
            if (fragmentManager.findFragmentByTag(tag) == null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.flContent, fragment, tag)
                        .commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T extends Class> void launchActivity(@NonNull T clazz, @NonNull Activity thisActivity, @Nullable Bundle bundle) {
        Intent intent = new Intent(thisActivity, clazz);
        if (bundle != null) intent.putExtras(bundle);
        thisActivity.startActivity(intent);
    }

    public static <T extends Class> void launchActivity(@NonNull T clazz, @NonNull Activity thisActivity) {
        launchActivity(clazz, thisActivity, null);
    }
}
