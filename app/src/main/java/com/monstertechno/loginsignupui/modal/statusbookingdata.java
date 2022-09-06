package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class statusbookingdata {
    @SerializedName("user_id")
    @Expose
    String userid;

    @SerializedName("status")
    @Expose
    String status1;

    public statusbookingdata(String userid, String status1) {
        this.userid = userid;
        this.status1 = status1;
    }

}
