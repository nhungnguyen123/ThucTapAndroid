package com.uiapp.thuctap.mvp.welcome.login.signup.view;

import com.uiapp.thuctap.base.presenter.IView;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;

/**
 * Created by hongnhung on 7/23/16.
 */
public interface ISignUpView extends IView {
    void SignUpError(String message);

    void SignUpSuccess(SignUpResponse results);

    void loginError(String message);
    void loginSuccess(LoginResponse results);
}
