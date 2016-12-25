package com.uiapp.thuctap.mvp.client.vegetableclient.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.uiapp.thuctap.base.presenter.BasePresenter;
import com.uiapp.thuctap.interactor.api.ApiManager;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.interactor.api.response.user.AllUserResponse;
import com.uiapp.thuctap.interactor.api.response.vegetable.GetAllVegetableResponse;
import com.uiapp.thuctap.interactor.prefer.PreferManager;
import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.GardenClient;
import com.uiapp.thuctap.model.User;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.mvp.client.vegetableclient.view.IClientVegetableView;
import com.uiapp.thuctap.mvp.main.garden.client.presenter.IClientPresenter;
import com.uiapp.thuctap.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by hongnhung on 8/21/16.
 */
public class ClientVegetablePresenter extends BasePresenter<IClientVegetableView> implements IClientVegetablePresenter {
    public List<User> listUser = new ArrayList<>();
    public List<Vegetable> mListVegetable = new ArrayList<>();
    public List<GardenClient> mListGardenClient = new ArrayList<>();
    public List<Garden> mLisetAllGardenUser = new ArrayList<>();

    @Inject
    public ClientVegetablePresenter(ApiManager apiManager, PreferManager preferManager) {
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
                getView().getAllUserSuccess(listUser);
            }

            @Override
            public void failure(RestError error) {
                getView().getAllUserError(error.message);
            }
        });
    }

    /**
     * show
     * fragment Detail All Garden
     *
     * @param position
     */
    @Override
    public void clickDetail(int position) {
        mListGardenClient.get(position);
        String jsonsend = new Gson().toJson(mListGardenClient.get(position));


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
                sum(mListGardenClient, mLisetAllGardenUser);
                getView().getGardenSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getGardenError(error.message);
            }
        });
    }

    @Override
    public void sum(List<GardenClient> mListGardenClient, List<Garden> mLisetAllGardenUser) {
        for (int i = 0; i < mLisetAllGardenUser.size(); i++) {
            for (int j = 0; j < mLisetAllGardenUser.get(i).getVegetableList().size(); j++) {


                if (check(mLisetAllGardenUser.get(i).getVegetableList().get(j).getId(), mListGardenClient)) {
                    GardenClient gardenClient = new GardenClient();
                    gardenClient.setId(mLisetAllGardenUser.get(i).getVegetableList().get(j).getId());
                    gardenClient.setNameVegetable(mLisetAllGardenUser.get(i).getVegetableList().get(j).getName());
                    gardenClient.setDescription(mLisetAllGardenUser.get(i).getVegetableList().get(j).getDescription());
                    gardenClient.setTotal(1);
                    List<Garden> list = new ArrayList<>();
                    list.add(mLisetAllGardenUser.get(i));
                    gardenClient.setmListGarden(list);
                    mListGardenClient.add(gardenClient);
                } else {
                    for (int m = 0; m < mListGardenClient.size(); m++) {
                        if (mListGardenClient.get(m).getId().equalsIgnoreCase(mLisetAllGardenUser.get(i).getVegetableList().get(j).getId())) {
                            mListGardenClient.get(m).setTotal(mListGardenClient.get(m).getTotal() + 1);

                            List<Garden> list = new ArrayList<>();
                            for (int l = 0; l < mListGardenClient.get(m).getmListGarden().size(); l++) {
                                Garden garden = mListGardenClient.get(m).getmListGarden().get(l);
                                list.add(garden);
                            }
//                            LogUtils.logE("before", new Gson().toJson(list));
                            list.add(mLisetAllGardenUser.get(i));
                            mListGardenClient.get(m).setmListGarden(list);
                            LogUtils.logE("after", new Gson().toJson(mListGardenClient.get(m).getmListGarden()));
                        }
                    }
                }
            }
        }
        getView().sumSuccess();

    }


    @Override
    public void getAllVegetable() {
        getApiManager().getAllVegetable(new ApiCallback<GetAllVegetableResponse>() {
            @Override
            public void success(GetAllVegetableResponse res) {
                mListVegetable.addAll(res.getVegetables());
                getView().getAllVegetableSuccess();
            }

            @Override
            public void failure(RestError error) {
                getView().getAllUserError(error.message);
            }
        });
    }


//    public int checkPostition(String idVegetable, List<Garden> mLisetAllGardenUser,) {
//        for (int i = 0; i < mLisetAllGardenUser.size(); i++) {
//            for (int j = 0; j < mLisetAllGardenUser.get(i).getVegetableList().size(); j++) {
//                if (idVegetable.equalsIgnoreCase(mLisetAllGardenUser.get(i).getVegetableList().get(j).getId())) {
//                    return
//                }
//            }
//        }
//    }


//    public int setPosition(String idVegetable, List<GardenClient> mListGardenClient) {
//        for (int i = 0; i < mListGardenClient.size(); i++) {
//            if (idVegetable.equalsIgnoreCase(mListGardenClient.get(i).getId())) {
//                return i;
//            }
//        }
//    }

    public boolean check(String idVegetable, List<GardenClient> mListGardenClient) {
        int count = 0;
        for (int i = 0; i < mListGardenClient.size(); i++) {
            if (idVegetable.equalsIgnoreCase(mListGardenClient.get(i).getId())) {
                count++;
            }

        }

        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }
}
