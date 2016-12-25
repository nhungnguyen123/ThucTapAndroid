package com.uiapp.thuctap.mvp.main.garden.client.view;

import com.uiapp.thuctap.base.presenter.IView;

/**
 * Created by hongnhung on 7/29/16.
 */
public interface IClientView extends IView {

    void getAllUserSuccess();
    void getAllUserError(String message);
    void getAllVegetableSuccess();
    void getAlVegetableError(String message);
}
