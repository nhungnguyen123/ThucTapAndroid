package com.uiapp.thuctap.interactor.api.response.garden;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;
import com.uiapp.thuctap.interactor.api.response.Results;
import com.uiapp.thuctap.model.Garden;

import java.util.ArrayList;
import java.util.List;


public class AllGardenResponse extends BaseResponse {
    @SerializedName("results")
    @Expose
    private List<Garden> list = new ArrayList<>();

    public List<Garden> getList() {
        return list;
    }

    public void setList(List<Garden> list) {
        this.list = list;
    }
}
