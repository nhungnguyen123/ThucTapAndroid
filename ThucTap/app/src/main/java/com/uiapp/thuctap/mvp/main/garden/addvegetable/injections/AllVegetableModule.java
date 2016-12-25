package com.uiapp.thuctap.mvp.main.garden.addvegetable.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.presenter.AllVegetablePresenter;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.presenter.GardenDetailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 8/15/16.
 */

@Module
public class AllVegetableModule {

    @Provides
    @PerFragment
    AllVegetablePresenter provideAllVegetablePresenter(ApiManager apiManager, PreferManager preferManager) {
        return new AllVegetablePresenter(apiManager, preferManager);
    }
}
