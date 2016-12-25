package com.uiapp.thuctap.mvp.main.garden.menucontainer.view;

import com.uiapp.thuctap.base.presenter.IView;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;

/**
 * Created by hongnhung on 7/27/16.
 */
public interface IMenuContainerView extends IView {


    void getAllGardensSuccess();

    void getAllGardenError(String message);

    void doLogOut();

    void goToEditGarden();
    void goCreateGarden();
    void goAllVegetable();
    void goAllUser();

    void goToCreateVegetable(String vegetableDetail);
}
