package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDataUser {
    @SerializedName("user_profile")
    @Expose
    private List<UserProfile> userProfile = null;

    public List<UserProfile> getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(List<UserProfile> userProfile) {
        this.userProfile = userProfile;
    }

}

