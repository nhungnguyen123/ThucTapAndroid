package com.uiapp.thuctap.mvp.main.garden.garden.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.garden.presenter.GardenPresenter;
import com.uiapp.thuctap.mvp.main.garden.garden.view.IGardenView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 7/24/16.
 */

@PerFragment
@Module
public class GardenModule {


    private IGardenView iGardenView;

    public GardenModule(IGardenView iGardenView) {
        this.iGardenView = iGardenView;

    }

    @Provides
    GardenPresenter provideGardenPresenter(ApiManager apiManager, PreferManager preferManager) {
        return new GardenPresenter(apiManager, preferManager);
    }
}
