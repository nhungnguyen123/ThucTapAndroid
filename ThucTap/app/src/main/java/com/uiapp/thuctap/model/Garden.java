package com.uiapp.thuctap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Garden implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("description")
    @Expose
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("vegetableList")
    @Expose
    private List<Vegetable> vegetableList;

    public String getArea() {
        return area;
    }


    public void setArea(String area) {
        this.area = area;
    }

    public List<Vegetable> getVegetableList() {
        return vegetableList;
    }

    public void setVegetableList(List<Vegetable> vegetableList) {
        this.vegetableList = vegetableList;
    }

    private boolean isChecked = false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public  boolean checkId(String id)
    {
        for (Vegetable v : vegetableList) {
            if(v.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }
}
