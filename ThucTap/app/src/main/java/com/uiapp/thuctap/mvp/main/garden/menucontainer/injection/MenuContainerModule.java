package com.uiapp.thuctap.mvp.main.garden.menucontainer.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.presenter.MenuContainerPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class MenuContainerModule {
    @Provides
    @PerFragment
    MenuContainerPresenter provideMenuContainerPresenter(ApiManager apiManager, PreferManager preferManager) {
        return new MenuContainerPresenter(apiManager, preferManager);
    }
}
