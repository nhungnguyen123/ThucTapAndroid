package com.uiapp.thuctap.mvp.main.garden.editgarden.view;

import com.uiapp.thuctap.base.presenter.IView;
import com.uiapp.thuctap.model.User;

import java.util.List;

/**
 * Created by hongnhung on 8/4/16.
 */
public interface IEditGardenView extends IView {

    void getAllGardenSuccess();

    void getAllGardenError(String error);

    void updateGardenSuccess();

    void updateGardenError(String message);

    void deleteGardenSuccess();

    void deleteGardenError(String message);
    void getAllUserSuccess(List<User> users);
    void getAllUserError(String message);
    void getGardenSuccess();

    void getGardenError(String error);




}
