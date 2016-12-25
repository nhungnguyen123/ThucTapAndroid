package com.uiapp.thuctap.mvp.client.vegetableclient.presenter;

import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.GardenClient;

import java.util.List;

/**
 * Created by hongnhung on 8/21/16.
 */
public interface IClientVegetablePresenter {
    void getAllVegetable();

    void getAllUser();

    void clickDetail(int position);

    void getGarden(String userId);


    void sum (List<GardenClient> mListGardenClient , List<Garden> mLisetAllGardenUser);




}
