package com.uiapp.thuctap.mvp.main.garden.vegetable.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.vegetable.presenter.VegetablePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 8/9/16.
 */
@Module
public class VegetableModule {
    @Provides
    @PerFragment
    VegetablePresenter provideVegetablePresenter(ApiManager apiManager, PreferManager preferManager) {
        return new VegetablePresenter(apiManager, preferManager);
    }
}
