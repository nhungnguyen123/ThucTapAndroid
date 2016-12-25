package com.uiapp.thuctap.interactor.api.network;

import com.uiapp.thuctap.interactor.api.response.BaseResponse;


public abstract class ApiCallback<T extends BaseResponse> {
    public abstract void success(T res);

    public abstract void failure(RestError error);
}
