package com.uiapp.thuctap.interactor.api.response.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.interactor.api.response.BaseResponse;
import com.uiapp.thuctap.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongnhung on 7/29/16.
 */
public class AllUserResponse extends BaseResponse {

    @SerializedName("results")
    @Expose
    private List<User> listUser;

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }
}
