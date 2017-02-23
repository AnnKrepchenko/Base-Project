package com.krepchenko.base_proj.db;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.novoda.sqliteprovider.demo.simple.DB;

import novoda.lib.sqliteprovider.provider.SQLiteContentProviderImpl;

/**
 * Created by Ann
 */

public class AppContentProvider extends SQLiteContentProviderImpl {
    public static final String DB_URI = "com.krepchenko.base_proj";
    public static final String AUTHORITY = "content://" + DB_URI + "/";

    public static final Uri TEMP = Uri.parse(AUTHORITY).buildUpon().appendPath(DB.Tables.Temp).build();


    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
