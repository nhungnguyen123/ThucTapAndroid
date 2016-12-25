package com.uiapp.thuctap.mvp.welcome.login.signup.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.presenter.LoginPresenter;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.view.ILoginView;
import com.uiapp.thuctap.mvp.welcome.login.signup.presenter.SignUpPresenter;
import com.uiapp.thuctap.mvp.welcome.login.signup.view.ISignUpView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hongnhung on 7/23/16.
 */
@Module
public class SignUpModule {

    private ISignUpView iSignUpView;

    public SignUpModule(ISignUpView iLoginView) {
        this.iSignUpView = iSignUpView;

    }
    @PerFragment
    @Provides
    SignUpPresenter provideSignUpPresenter(ApiManager apiManager, PreferManager preferManager){
        return  new SignUpPresenter(apiManager,preferManager);
    }
}
