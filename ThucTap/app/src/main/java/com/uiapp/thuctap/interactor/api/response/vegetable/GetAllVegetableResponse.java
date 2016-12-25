package com.uiapp.thuctap.interactor.api.response.vegetable;

import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;
import com.uiapp.thuctap.model.Vegetable;

import java.util.List;

/**
 * Created by hongnhung on 8/10/16.
 */
public class GetAllVegetableResponse extends BaseResponse {
    @SerializedName("results")
    List<Vegetable> vegetables;

    public List<Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }
}
