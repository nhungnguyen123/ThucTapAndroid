package com.uiapp.thuctap.interactor.api.response.user;

import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;
import com.uiapp.thuctap.interactor.api.response.Results;


public class SignUpResponse extends BaseResponse {
    @SerializedName("results")
    public Results results;
}
