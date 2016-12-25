package com.uiapp.thuctap.mvp.client.vegetableclient.view;

import com.uiapp.thuctap.base.presenter.IView;
import com.uiapp.thuctap.model.User;

import java.util.List;

/**
 * Created by hongnhung on 8/21/16.
 */
public interface IClientVegetableView extends IView {
    void getAllUserSuccess(List<User> users);

    void getAllUserError(String message);

    void getAllVegetableSuccess();

    void getAlVegetableError(String message);

    void getGardenSuccess();

    void getGardenError(String error);


    void sumSuccess();
}
