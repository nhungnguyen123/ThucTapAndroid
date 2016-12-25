package com.uiapp.thuctap.utils;

import android.util.Log;

import com.uiapp.thuctap.BuildConfig;

/**
 * Created by hongnhung on 7/18/16.
 */
public class LogUtils {
    private static final String LOG_PREFIX="LogUtils_";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 30;
    public LogUtils(){

    }
    public static String makeLogTag(String str){
        if(str.length()>MAX_LOG_TAG_LENGTH-LOG_PREFIX_LENGTH){
            return LOG_PREFIX+str.substring(0,MAX_LOG_TAG_LENGTH-1);
        }
        return LOG_PREFIX+str;
    }


    public static String makeLogTag(Class cls){
        return makeLogTag(cls.getSimpleName());
    }
    public static void logE(String tag, String message){
        if(BuildConfig.DEBUG){
            Log.e(tag,message);
        }
    }

    public static void logE(String tag, String message, Throwable cause){
        if(BuildConfig.DEBUG){
            Log.e(tag,message,cause);
        }
    }
}
