package com.uiapp.thuctap.mvp.main.garden.editgarden.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.main.garden.editgarden.presenter.EditGardenPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 8/4/16.
 */

@Module
public class EditGardenModule {

    @Provides
    @PerFragment
    EditGardenPresenter provideEditGardenPresenter (ApiManager apiManager, PreferManager preferManager) {
        return new EditGardenPresenter(apiManager, preferManager);
    }
}
