package com.uiapp.thuctap.mvp.main.garden.gardenclient.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.presenter.GardenClientPresenter;

import javax.annotation.PreDestroy;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 7/31/16.
 */

@Module
public class GardenClientModule {
    @Provides
    @PerFragment
    GardenClientPresenter provideGardenClientPresenter(ApiManager apiManager, PreferManager preferManager) {
        return new GardenClientPresenter(apiManager, preferManager);
    }
}
