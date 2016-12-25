package com.uiapp.thuctap.mvp.main.garden.addvegetable.presenter;

import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.vegetable.GetAllVegetableResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.addvegetable.view.IAllVegetableView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hongnhung on 8/15/16.
 */
public class AllVegetablePresenter extends BasePresenter<IAllVegetableView> implements IAllVegetablePresenter {
    public List<Vegetable> listVegetables = new ArrayList<>();

    @Inject
    public AllVegetablePresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }

    @Override
    public void getAllVegetable() {
        getApiManager().getAllVegetable(new ApiCallback<GetAllVegetableResponse>() {
            @Override
            public void success(GetAllVegetableResponse res) {
                listVegetables.clear();
                listVegetables.addAll(res.getVegetables());
                getView().getAllVegetableSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getAllVegetanleError(error.message);
            }
        });
    }

}
