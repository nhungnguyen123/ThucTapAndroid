package com.uiapp.thuctap.mvp.main.garden.client.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.client.view.ClientFragment;
import com.uiapp.thuctap.mvp.main.garden.garden.injection.GardenModule;
import com.uiapp.thuctap.mvp.main.garden.garden.view.GardenFragment;

import dagger.Component;

/**
 * Created by hongnhung on 7/29/16.
 */


@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = ClientModule.class)
public interface ClientComponent {
    void inject(ClientFragment fragment);
}
