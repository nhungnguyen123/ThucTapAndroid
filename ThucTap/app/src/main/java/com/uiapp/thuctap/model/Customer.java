package com.uiapp.thuctap.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hongnhung on 10/22/16.
 */

public class Customer implements Serializable {

    @SerializedName("tenDichVu")
    @Expose
    public String tenDichVu;

    @SerializedName("mota")
    @Expose
    public String mota;

    @SerializedName("phiTheoGio")
    @Expose
    public int phi;

    public Customer(String tenDichVu, String mota, int phi) {
        this.tenDichVu = tenDichVu;
        this.mota = mota;
        this.phi = phi;
    }
}
