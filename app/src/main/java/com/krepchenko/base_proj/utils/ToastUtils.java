package com.krepchenko.base_proj.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {
    private static final String TAG = ToastUtils.class.getSimpleName();

    public static void showSimpleToast(Context context, int resId) {
        showSimpleToast(context, resId, Toast.LENGTH_SHORT);
    }

    public static void showSimpleToast(Context context, String msg) {
        showSimpleToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showSimpleToast(Context context, int resId, int duration) {
        showSimpleToast(context, Gravity.BOTTOM, resId, duration);
    }

    public static void showSimpleToast(Context context, String msg, int duration) {
        showSimpleToast(context, Gravity.BOTTOM, msg, duration);
    }

    public static void showSimpleToast(Context context, int garvity, int resId, int duration) {
        if (context != null) {
            Toast toast = Toast.makeText(context, resId, duration);
            toast.setGravity(garvity, 0, 50);
            toast.show();
        }
    }

    public static void showSimpleToast(Context context, int garvity, String msg, int duration) {
        if (context != null && !TextUtils.isEmpty(msg)) {
            Toast toast = Toast.makeText(context, msg, duration);
            toast.setGravity(garvity, 0, 50);
            toast.show();
        }
    }


}
