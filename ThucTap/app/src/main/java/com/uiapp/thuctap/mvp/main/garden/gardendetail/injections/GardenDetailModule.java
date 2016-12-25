package com.uiapp.thuctap.mvp.main.garden.gardendetail.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.presenter.GardenDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 8/13/16.
 */

@Module
public class GardenDetailModule {
    @Provides
    @PerFragment
    GardenDetailPresenter provideGardenDetailPresenter(ApiManager apiManager, PreferManager preferManager) {
        return new GardenDetailPresenter(apiManager, preferManager);
    }
}
