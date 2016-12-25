package com.uiapp.thuctap.injection.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.uiapp.thuctap.interactor.prefer.PreferManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 7/19/16.
 */

@Module
public class PreferModule {
    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    PreferManager providePreferManager(SharedPreferences preferences) {
        return new PreferManager(preferences);
    }

}
