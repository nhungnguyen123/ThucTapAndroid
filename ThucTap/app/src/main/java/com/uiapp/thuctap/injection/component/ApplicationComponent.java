package com.uiapp.thuctap.injection.component;

import android.app.Application;
import android.content.Context;

import com.uiapp.thuctap.MainApplication;
import com.uiapp.thuctap.base.fragment.BaseFragment;
import com.uiapp.thuctap.injection.ApplicationContext;
import com.uiapp.thuctap.injection.module.ApiModule;
import com.uiapp.thuctap.injection.module.ApplicationModule;
import com.uiapp.thuctap.injection.module.PreferModule;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, PreferModule.class})
public interface ApplicationComponent {
    void inject(MainApplication mainApplication);

    void inject(BaseFragment baseFragment);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    ApiManager getApiManager();

    PreferManager getPreferManager();
}
