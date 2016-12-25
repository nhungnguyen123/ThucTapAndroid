package com.uiapp.thuctap.mvp.main.garden.vegetable.presenter;

import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.request.VegetableBodyRequest;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.interactor.api.response.vegetable.GetAllVegetableResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.vegetable.view.IVegetableView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class VegetablePresenter extends BasePresenter<IVegetableView> implements IVegetablePresenter {
    public List<Vegetable> listVegetables = new ArrayList<>();


    @Inject
    public VegetablePresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }

    @Override
    public void createVegetable(VegetableBodyRequest vegetableBodyRequest) {
        getApiManager().createVegetable(getUserToken(), vegetableBodyRequest, new ApiCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                getView().createVegetableSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().createVegetableError(error.message);
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
                listVegetables.clear();
                listVegetables.addAll(res.getVegetables());
                getView().getAllVegetableSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getAllVegetableError(error.message);
            }
        });
    }

    @Override
    public void checkVegetable(String nameVegetable) {
        int count = 0;
        for (int i = 0; i < listVegetables.size(); i++) {
            if (listVegetables.get(i).getName().equalsIgnoreCase(nameVegetable)) {
                count++;
                break;
            }
        }
        if (count != 0) {
            getView().checkVegetableError("Name Vegetable Is Ready");
        } else {
            getView().checkVegetableSuccess();
        }
    }
}
