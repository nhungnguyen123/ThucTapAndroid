package com.uiapp.thuctap.mvp.main.garden.vegetable.presenter;

import com.uiapp.thuctap.interactor.api.request.VegetableBodyRequest;

/**
 * Created by hongnhung on 8/9/16.
 */
public interface IVegetablePresenter {

    void createVegetable(VegetableBodyRequest createVegetable);

    String getUserToken();

    void getAllVegetable();

    void checkVegetable(String nameVegetable);
}
