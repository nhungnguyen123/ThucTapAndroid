package com.uiapp.thuctap.mvp.main.garden.gardenclient.presenter;

import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.mvp.main.garden.gardenclient.view.IGardenClientView;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongnhung on 7/31/16.
 */
public class GardenClientPresenter extends BasePresenter<IGardenClientView> implements IGardenClientPresenter {

    public List<Garden> listGarden = new ArrayList<>();
    public List<Garden> mLisetAllGardenUser = new ArrayList<>();

    public GardenClientPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }


    @Override
    public void getAllGardenClient(String userId) {
        getApiManager().getAllGardenClient(userId, new ApiCallback<AllGardenResponse>() {
            @Override
            public void success(AllGardenResponse res) {
                listGarden.clear();

                listGarden.addAll(res.getList());
                LogUtils.logE("size",res.getList().size()+"");
                getView().getAllGardenSuccess();
            }

            @Override
            public void failure(RestError error) {

                getView().getAllGardenError(error.message);
            }
        });
    }
}
