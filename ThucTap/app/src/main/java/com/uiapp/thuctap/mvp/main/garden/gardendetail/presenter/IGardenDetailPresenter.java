package com.uiapp.thuctap.mvp.main.garden.gardendetail.presenter;

import com.uiapp.thuctap.model.Garden;
import com.uiapp.thuctap.model.Vegetable;

/**
 * Created by hongnhung on 8/13/16.
 */
public interface IGardenDetailPresenter {


    String getUserToken();

    void setGardenDetail(Garden gardenDetail);

    void setVegetableDetail(int  vegetableDetail);


}
