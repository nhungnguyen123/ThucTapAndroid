package com.uiapp.thuctap.mvp.main.garden.gardendetail.presenter;

import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.gardendetail.view.IGardenDetailView;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class GardenDetailPresenter extends BasePresenter<IGardenDetailView> implements IGardenDetailPresenter {

    Garden garden = new Garden();
    List<Vegetable> mListVegetable = new ArrayList<>();

    @Inject
    public GardenDetailPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }


    @Override
    public String getUserToken() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_USER_TOKEN);
    }

    @Override
    public void setGardenDetail(Garden gardenDetail) {
        mListVegetable.clear();
        garden = gardenDetail;
        mListVegetable.addAll(garden.getVegetableList());
    }

    @Override
    public void setVegetableDetail(int vegetableDetail) {


        Vegetable vegetable = mListVegetable.get(vegetableDetail);

        getView().setVegetableDetail(vegetable);
    }


}
