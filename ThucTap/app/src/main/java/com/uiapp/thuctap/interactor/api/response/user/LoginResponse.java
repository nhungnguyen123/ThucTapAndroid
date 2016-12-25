package com.uiapp.thuctap.interactor.api.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;
import com.uiapp.thuctap.interactor.api.response.Results;

/**
 * Created by hongnhung on 7/23/16.
 */
public class LoginResponse extends BaseResponse {

    @SerializedName("tenDichVu")
    @Expose
    public String tenDV;

    @SerializedName("mota")
    @Expose
    public String moTa;

    @SerializedName("phiTheoGio")
    @Expose
    public int phi;

    @SerializedName("_id")
    @Expose
    public String id;
}
