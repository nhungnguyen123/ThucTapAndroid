package com.uiapp.thuctap.mvp.welcome.login.login.injection.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.presenter.LoginPresenter;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.view.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 7/20/16.
 */

@PerFragment
@Module
public class LoginModule {
    private ILoginView iLoginView;

    public LoginModule(ILoginView iLoginView) {
        this.iLoginView = iLoginView;

    }

    @Provides
    LoginPresenter provideloginPresenter(ApiManager apiManager, PreferManager preferManager) {
        return new LoginPresenter(apiManager, preferManager);
    }
}
