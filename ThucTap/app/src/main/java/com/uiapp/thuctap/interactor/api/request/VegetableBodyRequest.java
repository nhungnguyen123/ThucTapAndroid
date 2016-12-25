package com.uiapp.thuctap.interactor.api.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hongnhung on 8/10/16.
 */
public class VegetableBodyRequest {
    @SerializedName("name")
    public String name;

    @SerializedName("finishAt")
    public String finishAt;

    @SerializedName("createdAt")
    public String createdAt;

    @SerializedName("pesticides")
    public String pesticides;

    @SerializedName("ph")
    public String ph;

    @SerializedName("description")
    public String description;


    public VegetableBodyRequest(String name, String finishAt, String createdAt, String pesticides, String ph, String description) {
        this.name = name;
        this.ph = ph;
        this.finishAt = finishAt;
        this.createdAt = createdAt;
        this.pesticides = pesticides;
        this.description = description;
    }
}
