package com.uiapp.thuctap.mvp.main.garden.gardendetail.view;

import com.uiapp.thuctap.base.presenter.IView;
import com.uiapp.thuctap.model.Vegetable;

/**
 * Created by hongnhung on 8/13/16.
 */
public interface IGardenDetailView extends IView {
    void setVegetableDetail(Vegetable vegetableDetail);
}
