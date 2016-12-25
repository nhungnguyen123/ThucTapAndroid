package com.uiapp.thuctap.mvp.main.garden.client.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.client.presenter.ClientPresenter;
import com.uiapp.thuctap.mvp.main.garden.client.view.IClientView;
import com.uiapp.thuctap.mvp.main.garden.garden.presenter.GardenPresenter;
import com.uiapp.thuctap.mvp.main.garden.garden.view.IGardenView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 7/29/16.
 */

@PerFragment
@Module
public class ClientModule {
    private IClientView iClientView;

    public ClientModule(IClientView iClientView) {
        this.iClientView = iClientView;

    }

    @Provides
    ClientPresenter provideClientPresenter(ApiManager apiManager, PreferManager preferManager) {
        return new ClientPresenter(apiManager, preferManager);
    }
}
