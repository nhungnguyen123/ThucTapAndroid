package com.uiapp.thuctap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hongnhung on 8/10/16.
 */
public class Vegetable implements Serializable {

    @SerializedName("_id")
    @Expose
    private String Id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("finishAt")
    @Expose
    private String finishAt;

    @SerializedName("fertilization")
    @Expose
    private String fertilization;

    @SerializedName("pesticides")
    @Expose
    private String pesticides;


    @SerializedName("createdAt")
    @Expose
    private String createdAt;


    @SerializedName("description")
    @Expose
    private String description;



    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean choose = false;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("ph")
    @Expose
    private String ph;

    public Vegetable(String name, String id) {
        this.name = name;
        this.Id = id;
    }

    public Vegetable() {

    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }


    public boolean isChoose() {
        return choose;
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(String finishAt) {
        this.finishAt = finishAt;
    }

    public String getFertilization() {
        return fertilization;
    }

    public void setFertilization(String fertilization) {
        this.fertilization = fertilization;
    }

    public String getPesticides() {
        return pesticides;
    }

    public void setPesticides(String pesticides) {
        this.pesticides = pesticides;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
