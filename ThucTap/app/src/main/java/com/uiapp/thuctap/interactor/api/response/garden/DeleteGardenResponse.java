package com.uiapp.thuctap.interactor.api.response.garden;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;
import com.uiapp.thuctap.interactor.api.response.Results;

/**
 * Created by hongnhung on 8/6/16.
 */
public class DeleteGardenResponse extends BaseResponse {

    @SerializedName("results")
    public Results results;


}
