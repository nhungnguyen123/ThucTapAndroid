package com.uiapp.thuctap.mvp.main.garden.addvegetable.view;

import com.uiapp.thuctap.base.presenter.IView;

/**
 * Created by hongnhung on 8/15/16.
 */
public interface IAllVegetableView extends IView {
    void getAllVegetableSuccess();

    void getAllVegetanleError(String error);
}
