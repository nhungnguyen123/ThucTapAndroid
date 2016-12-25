package com.uiapp.thuctap.mvp.main.garden.gardenclient.injections;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.view.GardenClientFragment;

import dagger.Component;

/**
 * Created by hongnhung on 7/31/16.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = GardenClientModule.class)
public interface GardenClientComponent {
    void inject(GardenClientFragment gardenClientFragment);
}
