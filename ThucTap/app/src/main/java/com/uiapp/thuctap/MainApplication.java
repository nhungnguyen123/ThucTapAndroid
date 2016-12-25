package com.uiapp.thuctap;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.injection.component.DaggerApplicationComponent;
import com.uiapp.thuctap.injection.module.ApiModule;
import com.uiapp.thuctap.injection.module.ApplicationModule;
import com.uiapp.thuctap.injection.module.PreferModule;

/**
 * Created by hongnhung on 7/6/16.
 */

//TODO thực hiện ApplicationComponent

public class MainApplication extends Application {


    ApplicationComponent appComponent;

    private static Context mContext;
    private static Handler mHandlerUIThread;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }
    public static Context getContext(){
        return mContext;
    }

    public static Handler getHandlerUITHread(){
        return mHandlerUIThread;
    }

    public static ApplicationComponent getApplicationComponent(Context context) {
        MainApplication app = (MainApplication) context.getApplicationContext();
        if (app.appComponent == null) {
            app.appComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(app))
                    .apiModule(new ApiModule())
                    .preferModule(new PreferModule())
                    .build();
        }
        return app.appComponent;
    }




}
