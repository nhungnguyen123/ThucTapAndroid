package com.uiapp.thuctap.interactor.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hongnhung on 7/6/16.
 */
public abstract class BaseResponse {

    @SerializedName("success")
    public boolean success;

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;
}
