package com.uiapp.thuctap.mvp.main.garden.addvegetable.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.view.AllVegetableFragement;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.injections.GardenDetailModule;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.view.GardenDetailFragment;

import dagger.Component;

/**
 * Created by hongnhung on 8/15/16.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = AllVegetableModule.class)
public interface AllVegetableComponent {
    void inject(AllVegetableFragement allVegetableFragement);
}
