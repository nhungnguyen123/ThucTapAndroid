package com.uiapp.thuctap.mvp.main.garden.vegetable.view;

import com.uiapp.thuctap.base.presenter.IView;

/**
 * Created by hongnhung on 8/9/16.
 */
public interface IVegetableView extends IView {

    void createVegetableSuccess();
    void createVegetableError(String error);

    void getAllVegetableSuccess();
    void getAllVegetableError(String message);
    void checkVegetableSuccess();
    void checkVegetableError(String message);
}
