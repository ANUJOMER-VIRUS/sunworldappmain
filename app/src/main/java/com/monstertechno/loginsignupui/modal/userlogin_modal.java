package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class userlogin_modal {
    @SerializedName("mobile_number")
    private String number;
    @SerializedName("status")
    private boolean status;
    @SerializedName("user_id")
    private String userid;
@SerializedName("is_new_user")
private boolean newuser;
    public userlogin_modal(String number) {
        this.number = number;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isNewuser() {
        return newuser;
    }

    public String getUserid() {
        return userid;
    }


}
