package com.uiapp.thuctap.mvp.main.garden.garden.presenter;

import com.uiapp.thuctap.interactor.api.request.GardenBodyRequest;

/**
 * Created by hongnhung on 7/24/16.
 */
public interface IGardenPresenter {
    void createGarden(GardenBodyRequest gardenBodyRequest);

    String getUserToken();

    void getAllVegetable();

}
