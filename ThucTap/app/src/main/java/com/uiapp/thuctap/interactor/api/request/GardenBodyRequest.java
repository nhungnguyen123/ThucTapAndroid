package com.uiapp.thuctap.interactor.api.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hongnhung on 8/7/16.
 */
public class GardenBodyRequest implements Serializable {
    @SerializedName("name")
    public String name;

    @SerializedName("address")
    public String address;

    @SerializedName("user")
    public String user;

    @SerializedName("area")
    public String area;

    @SerializedName("vegetableList")
    public List<String> vegetableList;

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

    public List<String> getVegetableList() {
        return vegetableList;
    }

    public void setVegetableList(List<String> vegetableList) {
        this.vegetableList = vegetableList;
    }
}
