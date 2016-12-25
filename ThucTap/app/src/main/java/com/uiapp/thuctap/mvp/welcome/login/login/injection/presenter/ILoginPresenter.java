package com.uiapp.thuctap.mvp.welcome.login.login.injection.presenter;

/**
 * Created by hongnhung on 7/20/16.
 */
public interface ILoginPresenter {
    void login(String email, String password);

    void setKeyUserToken(String token);

    String getKeyUserToken();

    void setNameDisPlay(String nameDisPlay);

    void setRoleUser(String roleUser);


}
