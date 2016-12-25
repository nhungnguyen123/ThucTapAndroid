package com.uiapp.thuctap.mvp.welcome.login.login.injection.presenter;

import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.view.ILoginView;
import com.uiapp.thuctap.utils.LogUtils;

import javax.inject.Inject;


public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter {

    static final String TAG = LoginPresenter.class.getSimpleName();

    @Inject
    public LoginPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }

    @Override
    public void login(String email, String password) {
//        getApiManager().login(email, password, new ApiCallback<LoginResponse>() {
//            @Override
//            public void success(LoginResponse res) {
//                if (!isViewAttached()) return;
//                getView().loginSuccess(res);
//            }
//
//            @Override
//            public void failure(RestError error) {
//                getView().loginError(error.message);
//                LogUtils.logE(TAG, error.message);
//            }
//        });
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
