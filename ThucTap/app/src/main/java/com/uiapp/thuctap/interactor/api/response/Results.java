package com.uiapp.thuctap.interactor.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hongnhung on 7/23/16.
 */
public class Results {
    @SerializedName("_id")
    @Expose
    private String Id;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("username")
    @Expose
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @SerializedName("roles")

    @Expose
    private String roles;


    @SerializedName("displayName")
    @Expose
    private String displayName;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
