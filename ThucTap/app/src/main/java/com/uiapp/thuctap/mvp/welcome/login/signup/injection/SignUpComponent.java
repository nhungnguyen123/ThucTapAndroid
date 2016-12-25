package com.uiapp.thuctap.mvp.welcome.login.signup.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.injection.LoginModule;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.view.LoginFragment;
import com.uiapp.thuctap.mvp.welcome.login.signup.view.SignUpFragment;

import dagger.Component;

/**
 * Created by hongnhung on 7/23/16.
 */


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = SignUpModule.class)
public interface SignUpComponent {
    void inject(SignUpFragment fragment);
}
