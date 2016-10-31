package com.krepchenko.base_proj.ui.activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.krepchenko.base_proj.R;
import com.krepchenko.base_proj.databinding.ActivityMainBinding;
import com.krepchenko.base_proj.db.AppContentProvider;
import com.krepchenko.base_proj.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int LOADER_ID = 0;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID,Bundle.EMPTY,this);
        activityMainBinding = DataBindingUtil.setContentView(this,getContentView());
        activityMainBinding.textView.setText("DataBinding works");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, AppContentProvider.TEMP,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
