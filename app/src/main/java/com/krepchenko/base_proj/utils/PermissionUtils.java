package com.krepchenko.base_proj.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.krepchenko.base_proj.BuildConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * Utils for Android >=6.0(M) permission requests
 */
public class PermissionUtils {

    private static final String TAG = "PermissionUtils";

    /**
     * Request passed permissions
     * https://developer.android.com/training/permissions/requesting.html#perm-request
     *
     * @param activity       pass current activity
     * @param permissionCode code for request permission result
     * @param permissions    pass list of needed permissions
     */
    private static void requestPermissions(Activity activity, int permissionCode, String... permissions) {
        List<String> neededPermissions = new LinkedList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                neededPermissions.add(permission);
            }
        }

        if (neededPermissions.isEmpty())
            return;

        ActivityCompat.requestPermissions(
                activity,
                neededPermissions.toArray(new String[neededPermissions.size()]),
                permissionCode
        );

    }

    public static boolean isAllPermissionAdded(Activity activity, String... permissions) {
        boolean result = true;
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(activity, permission);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                result = false;
                break;
            }
        }

        return result;
    }

    /**
     * Asks for ACCESS_FINE_LOCATION permission
     * https://developer.android.com/training/permissions/requesting.html#perm-request
     *
     * @param activity       pass current activity
     * @param permissionCode code for request permission result
     */
    public static void fineLocation(Activity activity, int permissionCode) {
        if (BuildConfig.DEBUG) Log.d(TAG, "fineLocation");
        requestPermissions(activity, permissionCode, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * Asks for ACCESS_COARSE_LOCATION permission
     * https://developer.android.com/training/permissions/requesting.html#perm-request
     *
     * @param activity       pass current activity
     * @param permissionCode code for request permission result
     */
    public static void coarseLocation(Activity activity, int permissionCode) {
        if (BuildConfig.DEBUG) Log.d(TAG, "coarseLocation");
        requestPermissions(activity, permissionCode, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    /**
     * Asks for CAMERA permission
     * https://developer.android.com/training/permissions/requesting.html#perm-request
     *
     * @param activity       pass current activity
     * @param permissionCode code for request permission result
     */
    public static void camera(Activity activity, int permissionCode) {
        if (BuildConfig.DEBUG) Log.d(TAG, "camera");
        requestPermissions(activity, permissionCode, Manifest.permission.CAMERA);
    }

    /**
     * Asks for CALL_PHONE permission
     * https://developer.android.com/training/permissions/requesting.html#perm-request
     *
     * @param activity       pass current activity
     * @param permissionCode code for request permission result
     */
    public static void callPhone(Activity activity, int permissionCode) {
        if (BuildConfig.DEBUG) Log.d(TAG, "callPhone");
        requestPermissions(activity, permissionCode, Manifest.permission.CALL_PHONE);
    }

    /**
     * Asks for WRITE_EXTERNAL_STORAGE permission
     * https://developer.android.com/training/permissions/requesting.html#perm-request
     *
     * @param activity       pass current activity
     * @param permissionCode code for request permission result
     */
    public static void writeExternalStorage(Activity activity, int permissionCode) {
        if (BuildConfig.DEBUG) Log.d(TAG, "writeExternalStorage");
        requestPermissions(activity, permissionCode, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * Asks for passed permissions
     * https://developer.android.com/training/permissions/requesting.html#perm-request
     *
     * @param activity       pass current activity
     * @param permissionCode code for request permission result
     * @param permissions    pass list of needed permissions
     */
    public static void extraPermissions(Activity activity, int permissionCode, String... permissions) {
        if (BuildConfig.DEBUG) Log.d(TAG, "extraPermissions");
        requestPermissions(activity, permissionCode, permissions);
    }


}
