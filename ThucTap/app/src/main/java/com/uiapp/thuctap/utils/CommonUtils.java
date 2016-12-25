package com.uiapp.thuctap.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.uiapp.thuctap.MainApplication;

/**
 * Created by hongnhung on 7/6/16.
 */
public class CommonUtils {
    public static void showSnackBar(View view, String content, int time) {
        Snackbar.make(view, content, time).show();
    }
    public static void showToast(Context context , String text){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show();
    }


    public static boolean isNetworkConnect(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) MainApplication.getContext()
        .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return  networkInfo!= null;
    }
}
