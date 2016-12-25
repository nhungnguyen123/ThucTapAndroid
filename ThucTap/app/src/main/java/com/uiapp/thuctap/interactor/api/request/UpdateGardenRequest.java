package com.uiapp.thuctap.interactor.api.request;

import com.google.gson.annotations.SerializedName;
import com.uiapp.thuctap.model.Vegetable;
import com.uiapp.thuctap.model.VegetableBody;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongnhung on 8/12/16.
 */
public class UpdateGardenRequest implements Serializable {

    @SerializedName("name")
    public String name;

    @SerializedName("address")
    public String address;

    @SerializedName("user")
    public String user;

    @SerializedName("area")
    public String area;

    @SerializedName("vegetableList")
    public List<VegetableBody> vegetableList;

    public List<VegetableBody> getVegetableList() {
        return vegetableList;
    }

    public void setVegetableList(List<VegetableBody> vegetableList) {
        this.vegetableList = vegetableList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


}
