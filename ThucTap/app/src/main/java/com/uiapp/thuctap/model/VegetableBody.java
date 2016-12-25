package com.uiapp.thuctap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hongnhung on 8/12/16.
 */
public class VegetableBody implements Serializable {

    @SerializedName("_id")
    @Expose
    private String Id;

    @SerializedName("name")
    @Expose
    private String name;

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

    public VegetableBody(String name, String id) {
        this.name = name;
        Id = id;
    }
}
