package com.uiapp.thuctap.mvp.main.garden.vegetable.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.vegetable.view.VegetableFragment;

import dagger.Component;

/**
 * Created by hongnhung on 8/9/16.
 */
@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = VegetableModule.class)
public interface VegetableComponent {
    void inject(VegetableFragment vegetableFragment);
}
