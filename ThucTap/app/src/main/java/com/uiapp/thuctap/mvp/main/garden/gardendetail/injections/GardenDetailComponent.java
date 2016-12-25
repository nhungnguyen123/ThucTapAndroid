package com.uiapp.thuctap.mvp.main.garden.gardendetail.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.view.GardenDetailFragment;

import dagger.Component;

/**
 * Created by hongnhung on 8/13/16.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = GardenDetailModule.class)
public interface GardenDetailComponent {
    void inject(GardenDetailFragment gardenDetailFragment);
}
