package com.uiapp.thuctap.mvp.main.garden.garden.view;

import com.uiapp.thuctap.base.presenter.IView;
import com.uiapp.thuctap.interactor.api.response.garden.CreateGardenResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;

/**
 * Created by hongnhung on 7/24/16.
 */
public interface IGardenView extends IView {

    void createGardenSuccess(CreateGardenResponse signUpResponse);

    void createGardenFailer(String message);

    void getAllVegetableSuccess(String message);

    void getAllVegetableError(String message);
}
