package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class user_idmodal {
    @SerializedName("otp")
    private String otp;
    @SerializedName("user_id")
   private String user_id;

    public String getOtp() {
        return otp;
    }

    public String getUser_id() {
        return user_id;
    }

    public user_idmodal(String otp, String user_id) {
        this.otp = otp;
        this.user_id = user_id;
    }
}
