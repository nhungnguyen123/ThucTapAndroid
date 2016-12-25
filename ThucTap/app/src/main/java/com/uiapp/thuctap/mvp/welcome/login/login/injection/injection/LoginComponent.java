package com.uiapp.thuctap.mvp.welcome.login.login.injection.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.welcome.login.login.injection.view.LoginFragment;

import dagger.Component;


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
public interface LoginComponent {
    void inject(LoginFragment fragment);
}
