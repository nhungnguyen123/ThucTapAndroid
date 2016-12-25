package com.uiapp.thuctap.mvp.main.garden.menucontainer.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.view.MenuContainerFragment;

import dagger.Component;

/**
 * Created by hongnhung on 7/27/16.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = MenuContainerModule.class)
public interface MenuContainerComponent {
    void inject(MenuContainerFragment menuContainerFragment);
}
