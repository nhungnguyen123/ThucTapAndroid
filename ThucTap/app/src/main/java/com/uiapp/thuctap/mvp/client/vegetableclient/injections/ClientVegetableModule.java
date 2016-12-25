package com.uiapp.thuctap.mvp.client.vegetableclient.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.client.vegetableclient.presenter.ClientVegetablePresenter;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.presenter.AllVegetablePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 8/21/16.
 */

@Module
public class ClientVegetableModule {

    @Provides
    @PerFragment
    ClientVegetablePresenter provideClientVegetablePresenter(ApiManager apiManager, PreferManager preferManager) {
        return new ClientVegetablePresenter(apiManager, preferManager);
    }
}
