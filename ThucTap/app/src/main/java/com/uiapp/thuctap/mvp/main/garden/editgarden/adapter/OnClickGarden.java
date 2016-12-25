package com.uiapp.thuctap.mvp.main.garden.editgarden.adapter;

import com.uiapp.thuctap.interactor.api.request.GardenBodyRequest;

/**
 * Created by hongnhung on 8/11/16.
 */
public interface OnClickGarden {
    void OnClickDeleteGarden(String idGarden);

    void OnEditGarden(GardenBodyRequest gardenBodyRequest, String idGarden);
}
