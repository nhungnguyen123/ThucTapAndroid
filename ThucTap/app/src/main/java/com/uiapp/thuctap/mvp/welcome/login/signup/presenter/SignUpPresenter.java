package com.uiapp.thuctap.mvp.welcome.login.signup.presenter;

import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.presenter.ILoginPresenter;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.view.ILoginView;
import com.uiapp.thuctap.mvp.welcome.login.signup.view.ISignUpView;
import com.uiapp.thuctap.utils.LogUtils;

import javax.inject.Inject;


public class SignUpPresenter extends BasePresenter<ISignUpView> implements ISignUpPresenter {


    static final String TAG = SignUpPresenter.class.getSimpleName();
    @Inject
    public SignUpPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }
    @Override
    public void login(String email, String password) {
        getApiManager().login(email, password, new ApiCallback<LoginResponse>() {
            @Override
            public void success(LoginResponse res) {
                if (!isViewAttached()) return;
                getView().loginSuccess(res);
            }

            @Override
            public void failure(RestError error) {
                getView().loginError(error.message);
                LogUtils.logE(TAG, error.message);
            }
        });
    }

    @Override
    public void signUp(String firstName, String lastName, String displayName, String email, String password, String roles, String username) {
        getApiManager().signUp(firstName, lastName, displayName, email, password, roles, username, new ApiCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                getView().SignUpSuccess(res);
            }

            @Override
            public void failure(RestError error) {
                getView().SignUpError(error.message);

            }
        });
    }
    @Override
    public void setKeyUserToken(String token) {
        getPreferManager().setKeyValueByKeyName(AppConstants.KEY_USER_TOKEN, token);

    }


    @Override
    public String getKeyUserToken() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_USER_TOKEN);
    }

    @Override
    public void setNameDisPlay(String nameDisPlay) {
        getPreferManager().setKeyValueByKeyName(AppConstants.KEY_NAME_DISPLAY, nameDisPlay);
    }

    @Override
    public void setRoleUser(String roleUser) {
        getPreferManager().setKeyValueByKeyName(AppConstants.KEY_ROLE_USER, roleUser);
    }
}