package com.uiapp.thuctap.mvp.welcome.login.login.injection.view;

import com.uiapp.thuctap.base.presenter.IView;
import com.uiapp.thuctap.interactor.api.response.Results;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;

/**
 * Created by hongnhung on 7/6/16.
 */
public interface ILoginView extends IView {
    void loginError(String message);
    void loginSuccess(LoginResponse results);
}
