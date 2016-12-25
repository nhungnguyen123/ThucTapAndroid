package com.uiapp.thuctap.mvp.main.garden.garden.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.garden.view.GardenFragment;

import dagger.Component;

/**
 * Created by hongnhung on 7/24/16
 * .
 */


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = GardenModule.class)
public interface GardenComponent {

    void inject( GardenFragment fragment);
}
