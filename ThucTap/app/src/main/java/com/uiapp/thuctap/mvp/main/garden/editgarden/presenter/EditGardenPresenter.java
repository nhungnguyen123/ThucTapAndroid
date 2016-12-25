package com.uiapp.thuctap.mvp.main.garden.editgarden.presenter;

import com.google.gson.Gson;
import com.uiapp.thuctap.AppConstants;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.interactor.api.response.user.AllUserResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;
import com.uiapp.thuctap.model.GardenClient;
import com.uiapp.thuctap.model.User;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.main.garden.editgarden.view.IEditGardenView;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hongnhung on 8/4/16.
 */
public class EditGardenPresenter extends BasePresenter<IEditGardenView> implements IEditGardenPresenter {


    public List<User> listUser = new ArrayList<>();
    public List<Vegetable> mListVegetable = new ArrayList<>();
    public List<GardenClient> mListGardenClient = new ArrayList<>();
    public List<Garden> mLisetAllGardenUser = new ArrayList<>();


    public List<Garden> listGarden = new ArrayList<>();
    public String role = "";

    @Inject
    public EditGardenPresenter(ApiManager apiManager, PreferManager preferManager) {
        super(apiManager, preferManager);
    }

    @Override
    public void attachView(IEditGardenView mvpView) {
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
    public void getAllGarden() {
        getApiManager().getAllGarden(getUserToken(), new ApiCallback<AllGardenResponse>() {
            @Override
            public void success(AllGardenResponse res) {
                listGarden.clear();
                for (int i = 0; i < res.getList().size(); i++) {
                }
                listGarden.addAll(res.getList());
                getView().getAllGardenSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getAllGardenError(error.message);
            }
        });
    }

    @Override
    public void updateGarden(String idGarden, UpdateGardenRequest updateGardenRequest) {

        getApiManager().updateGarden(idGarden, updateGardenRequest, getUserToken(), new ApiCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                getView().updateGardenSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().updateGardenError(error.message);
            }
        });
    }

    @Override
    public void deleteGarden(final String idGarden) {
        LogUtils.logE("token", getUserToken() + "");
        getApiManager().deteleGarden(idGarden, getUserToken(), new ApiCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                for (int i = 0; i < listGarden.size(); i++) {
                    if (listGarden.get(i).getId().equals(idGarden)) {
                        listGarden.remove(i);
                    }
                }
                getView().deleteGardenSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().deleteGardenError(error.message);
            }
        });
    }

    @Override
    public String getUserToken() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_USER_TOKEN);
    }

    @Override
    public String getRoles() {
        return getPreferManager().getKeyValueByKeyName(AppConstants.KEY_ROLE_USER);
    }

    @Override
    public void getAllUser() {
        getApiManager().getAllUser(new ApiCallback<AllUserResponse>() {
            @Override
            public void success(AllUserResponse res) {
                listUser.clear();
                listUser.addAll(res.getListUser());
                LogUtils.logE("size", listUser.size() + "");
                getView().getAllUserSuccess(listUser);
            }

            @Override
            public void failure(RestError error) {
                getView().getAllUserError(error.message);
            }
        });

    }

    @Override
    public void getGarden(String userId) {
        getApiManager().getAllGardenClient(userId, new ApiCallback<AllGardenResponse>() {
            @Override
            public void success(AllGardenResponse res) {

                LogUtils.logE("allgarden", new Gson().toJson(res));
                for (int i = 0; i < res.getList().size(); i++) {
                    mLisetAllGardenUser.add(res.getList().get(i));
                }
                getView().getGardenSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getGardenError(error.message);
            }
        });
    }

}
