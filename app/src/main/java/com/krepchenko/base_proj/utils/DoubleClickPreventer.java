package com.krepchenko.base_proj.utils;

import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.v4.util.LongSparseArray;

public class DoubleClickPreventer {

    private static final long MIN_CLICK_INTERVAL = 1000;

    private static LongSparseArray<Long> timeClick = new LongSparseArray<>();

    public static void onClick(@IdRes int id, onNextListener listener){
        if (timeClick.get(id) == null){
            timeClick.put(id, SystemClock.uptimeMillis());
            listener.onNext();
            return;
        }

        if ((SystemClock.uptimeMillis() - timeClick.get(id) < MIN_CLICK_INTERVAL)){
            timeClick.put(id,SystemClock.uptimeMillis());
        } else {
            timeClick.put(id,SystemClock.uptimeMillis());
            listener.onNext();
        }
    }

    public interface onNextListener {
        void onNext();
    }
}
