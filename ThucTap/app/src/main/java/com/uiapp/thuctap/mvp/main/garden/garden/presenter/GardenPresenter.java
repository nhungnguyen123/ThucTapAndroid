package com.uiapp.thuctap.mvp.main.garden.garden.presenter;

import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.request.GardenBodyRequest;
import com.uiapp.thuctap.interactor.api.response.garden.CreateGardenResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.interactor.api.response.vegetable.GetAllVegetableResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.garden.view.IGardenView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hongnhung on 7/24/16.
 */
public class GardenPresenter extends BasePresenter<IGardenView> implements IGardenPresenter {

    public List<Vegetable> mListVegetables = new ArrayList<>();

    @Inject
    public GardenPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }

    @Override
    public void createGarden(GardenBodyRequest gardenBodyRequest) {
        getApiManager().createGarden(gardenBodyRequest, getUserToken(), new ApiCallback<CreateGardenResponse>() {
            @Override
            public void success(CreateGardenResponse res) {
                getView().createGardenSuccess(res);
            }

            @Override
            public void failure(RestError error) {
                getView().createGardenFailer(error.message);
            }
        });
    }

    @Override
    public String getUserToken() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_USER_TOKEN);
    }

    @Override
    public void getAllVegetable() {
        getApiManager().getAllVegetable(new ApiCallback<GetAllVegetableResponse>() {
            @Override
            public void success(GetAllVegetableResponse res) {
                mListVegetables.clear();
                mListVegetables.addAll(res.getVegetables());
                getView().getAllVegetableSuccess("Success");
            }

            @Override
            public void failure(RestError error) {
                getView().getAllVegetableError(error.message);
            }
        });
    }
}
