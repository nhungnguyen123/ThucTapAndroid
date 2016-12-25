package com.uiapp.thuctap.mvp.main.garden.editgarden.presenter;

import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;
import com.uiapp.thuctap.model.User;

import java.util.List;

/**
 * Created by hongnhung on 8/4/16.
 */
public interface IEditGardenPresenter {
    void getAllGarden();

    void updateGarden(String idGarden, UpdateGardenRequest gardenBodyRequest);

    void deleteGarden(String idGarden);

    String getUserToken();

    String getRoles();

    void getAllUser();
    void getGarden(String userId);

}
