package com.krepchenko.base_proj.utils;

import android.text.TextUtils;

import com.krepchenko.base_proj.BuildConfig;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

/**
 * Created by Ann
 */

public class TokenUtils {

    private static String TOKEN_PASS = "krepchenko";

    private static String encryptPass(){
        try {
            return AESCrypt.encrypt(BuildConfig.APPLICATION_ID,TOKEN_PASS);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptToken(String token){
        if(TextUtils.isEmpty(token)){
            return null;
        }
        try {
            return AESCrypt.encrypt(encryptPass(),token);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptToken(String token){
        if(TextUtils.isEmpty(token)){
            return null;
        }
        try {
            return AESCrypt.decrypt(encryptPass(),token);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
