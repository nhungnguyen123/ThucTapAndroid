package com.uiapp.thuctap.mvp.main.garden.editgarden.interfacecallback;

import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;
import com.uiapp.thuctap.model.Garden;

/**
 * Created by hongnhung on 8/5/16.
 */
public interface OnClickAction {
    void OnClickDelete(String idGarden);

    void OnClickChange(String idGarden, UpdateGardenRequest gardenBodyRequest);

    void OnClickDetail(String garden);
}
