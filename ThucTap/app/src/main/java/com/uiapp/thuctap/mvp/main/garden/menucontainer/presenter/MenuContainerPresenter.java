package com.uiapp.thuctap.mvp.main.garden.menucontainer.presenter;

import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.mvp.main.garden.menucontainer.view.IMenuContainerView;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MenuContainerPresenter extends BasePresenter<IMenuContainerView> implements IMenuContainerPresenter {


    public List<Garden> listGarden = new ArrayList<>();

    @Inject
    public MenuContainerPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }

    @Override
    public void attachView(IMenuContainerView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void getAllGardens() {
        getApiManager().getAllGarden(getUserToken(), new ApiCallback<AllGardenResponse>() {
            @Override
            public void success(AllGardenResponse res) {
                listGarden.clear();
                listGarden.addAll(res.getList());
                getView().getAllGardensSuccess();
            }

            @Override
            public void failure(RestError error) {
                if (error == null)

                {

                } else {
                    getView().getAllGardenError(error.message);

                }
            }
        });
    }

    @Override
    public String getUserToken() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_USER_TOKEN);
    }

    @Override
    public String getNameDisplay() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_NAME_DISPLAY);
    }

    @Override
    public String getRoleUser() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_ROLE_USER);
    }

    @Override
    public void getAllUser() {
        if (!isViewAttached()) return;
        getView().goAllUser();
    }

    @Override
    public void loadKiosk(int position) {

    }

    @Override
    public void doLogout() {
        if (!isViewAttached()) return;
        getPreferManager().removeKeyValueByKeyName(AppConstants.KEY_NAME_DISPLAY);
        getPreferManager().removeKeyValueByKeyName(AppConstants.KEY_USER_TOKEN);
        getPreferManager().removeKeyValueByKeyName(AppConstants.KEY_ROLE_USER);
        getView().doLogOut();

    }

    @Override
    public void goEditGarden() {
        if (!isViewAttached()) return;
        getView().goToEditGarden();
    }

    @Override
    public void getGarden() {

    }

    @Override
    public void createGarden() {
        if (!isViewAttached())
            return;
        getView().goCreateGarden();
    }

    @Override
    public void allVegetable() {
        if (!isViewAttached())
            return;
        getView().goAllVegetable();
    }

    @Override
    public void goCreateVegetable() {
        if (!isViewAttached()) return;
        getView().goToCreateVegetable("");
    }

    public List<Garden> getListGarden() {
        return listGarden;
    }
}
