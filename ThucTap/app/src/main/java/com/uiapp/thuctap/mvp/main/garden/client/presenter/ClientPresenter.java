package com.uiapp.thuctap.mvp.main.garden.client.presenter;

import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.user.AllUserResponse;
import com.uiapp.thuctap.interactor.api.response.vegetable.GetAllVegetableResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.User;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.client.view.IClientView;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ClientPresenter extends BasePresenter<IClientView> implements IClientPresenter {
    public List<User> listUser = new ArrayList<>();
    public List<Vegetable> mListVegetable = new ArrayList<>();

    @Inject
    public ClientPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }

    @Override
    public void getAllUser() {
        getApiManager().getAllUser(new ApiCallback<AllUserResponse>() {
            @Override
            public void success(AllUserResponse res) {

                listUser.clear();
                listUser.addAll(res.getListUser());
                LogUtils.logE("size", listUser.size() + "");

                getView().getAllUserSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getAllUserError(error.message);
            }
        });
    }

    @Override
    public void getAllVegetable() {
        getApiManager().getAllVegetable(new ApiCallback<GetAllVegetableResponse>() {
            @Override
            public void success(GetAllVegetableResponse res) {
                mListVegetable.addAll(res.getVegetables());
                getView().getAllUserSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getAllUserError(error.message);
            }
        });
    }
}
