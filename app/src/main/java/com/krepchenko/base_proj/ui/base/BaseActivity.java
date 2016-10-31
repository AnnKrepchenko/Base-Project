package com.krepchenko.base_proj.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by Ann on 01.09.2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static boolean isKeyboardOpened;
    private ProgressDialog mProgressDialog;
    private static boolean isProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mProgressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isProgress){
            showProgress();
        }
    }

    protected abstract int getContentView();

    public void setActionBar(Toolbar toolbar, boolean homeAsUp, boolean homeEnabled, String title) {

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(homeAsUp);
            actionBar.setHomeButtonEnabled(homeEnabled);
            if (title == null) {
                actionBar.setDisplayShowTitleEnabled(false);
            }
            if (!TextUtils.isEmpty(title)) {
                actionBar.setTitle(title);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgress(){
        isProgress = true;
        mProgressDialog.setTitle("Please wait...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public void setProgressMessage(String progressMessage){
        mProgressDialog.setMessage(progressMessage);
    }

    public void stopProgress(){
        isProgress= false;
        mProgressDialog.dismiss();
    }

    protected void startActivity(Class aClass) {
        startActivity(new Intent(this, aClass));
    }
    protected void startActivity(Class aClass,boolean clear) {
        if(clear) {
            startActivity(aClass, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        else {
            startActivity(aClass);
        }
    }

    protected void startActivity(Class aClass, int flags) {
        startActivity(aClass, flags, -1, -1);
    }

    protected void startActivity(Class aClass, int flags, int enterAnim, int exitAnim) {
        Intent intent = new Intent(this, aClass);
        intent.addFlags(flags);
        startActivity(intent);
        if (enterAnim != -1 && exitAnim != -1)
            overridePendingTransition(enterAnim, exitAnim);
    }


    public final void openKeyboard() {
        if (isKeyboardOpened)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            isKeyboardOpened = true;
        }
    }

    public final void hideKeyboard() {
        if (!isKeyboardOpened)
            return;
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            isKeyboardOpened = false;
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    protected boolean checkNetwork() {
        if (isNetworkConnected()) {
            return true;
        }
        /*final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(R.string.no_connection);
        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();*/
        return false;
    }
}
