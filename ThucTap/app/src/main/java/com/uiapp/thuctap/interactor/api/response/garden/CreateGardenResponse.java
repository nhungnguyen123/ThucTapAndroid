package com.uiapp.thuctap.interactor.api.response.garden;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;


public class CreateGardenResponse extends BaseResponse {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("create")
    @Expose
    private String create;
    @SerializedName("area")
    @Expose
    private Object area;
    @SerializedName("vegetableList")
    @Expose
    private List<Object> vegetableList = new ArrayList<Object>();
}
