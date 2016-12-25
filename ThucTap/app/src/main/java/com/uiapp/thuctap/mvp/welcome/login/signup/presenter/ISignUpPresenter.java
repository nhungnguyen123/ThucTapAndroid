package com.uiapp.thuctap.mvp.welcome.login.signup.presenter;

/**
 * Created by hongnhung on 7/23/16.
 */
public interface ISignUpPresenter {
    void setNameDisPlay(String nameDisPlay);

    String getKeyUserToken();

    void setRoleUser(String roleUser);

    void setKeyUserToken(String token);

    void login(String email, String password);

    void signUp(String firstName, String lastName, String displayName, String email, String password, String roles, String username);
}
