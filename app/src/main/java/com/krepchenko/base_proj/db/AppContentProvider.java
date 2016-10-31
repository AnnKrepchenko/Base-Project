package com.krepchenko.base_proj.db;

import android.net.Uri;

import com.novoda.sqliteprovider.demo.simple.DB;

import novoda.lib.sqliteprovider.provider.SQLiteContentProviderImpl;

/**
 * Created by Ann
 */

public class AppContentProvider extends SQLiteContentProviderImpl {
    public static final String AUTHORITY = "content://com.krepchenko.base_proj/";

    public static final Uri TEMP = Uri.parse(AUTHORITY).buildUpon().appendPath(DB.Tables.Temp).build();
}
