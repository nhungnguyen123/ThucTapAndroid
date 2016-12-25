package com.uiapp.thuctap.mvp.main.garden.editgarden.injection;

import com.uiapp.thuctap.injection.PerFragment;
import com.uiapp.thuctap.injection.component.ApplicationComponent;
import com.uiapp.thuctap.mvp.main.garden.editgarden.view.EditGardenFragment;

import dagger.Component;

/**
 * Created by hongnhung on 8/4/16.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = EditGardenModule.class)
public interface EditGardenComponent {

    void inject(EditGardenFragment editGardenFragment);
}
