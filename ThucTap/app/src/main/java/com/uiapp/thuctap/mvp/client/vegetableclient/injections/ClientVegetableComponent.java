package com.uiapp.thuctap.mvp.client.vegetableclient.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.client.vegetableclient.view.ClientVegetableFragment;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.injections.AllVegetableModule;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.view.AllVegetableFragement;

import dagger.Component;

/**
 * Created by hongnhung on 8/21/16.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = ClientVegetableModule.class)
public interface ClientVegetableComponent {
    void inject(ClientVegetableFragment clientVegetableFragment);
}
