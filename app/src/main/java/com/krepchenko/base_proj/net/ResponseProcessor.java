package com.krepchenko.base_proj.net;

import android.content.Context;
import android.widget.Toast;

import com.krepchenko.base_proj.net.core.GsonWrapper;
import com.krepchenko.base_proj.utils.ToastUtils;

/**
 * Created by Ann on 29.08.2016.
 */
public class ResponseProcessor {
    public static void showErrorMessage(Context context, Throwable error) {
        if (context != null) {
            ErrorResponse errorResponse = ResponseProcessor.getErrorResponse(error.getMessage());
            if (errorResponse != null) {
                ToastUtils.showSimpleToast(context, errorResponse.getMessage(), Toast.LENGTH_LONG);
            }
        }
    }

    public static ErrorResponse getErrorResponse(String error) {
        try {
            return GsonWrapper.getGson().fromJson(error, ErrorResponse.class);
        } catch (Exception e) {
            return null;
        }
    }

    public class ErrorResponse {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
