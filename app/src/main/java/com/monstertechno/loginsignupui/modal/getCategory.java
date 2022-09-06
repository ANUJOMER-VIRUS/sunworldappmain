package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class getCategory {
    @SerializedName("user_id")
    String userid;

    public getCategory(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @SerializedName("status")
    private boolean status;
    @SerializedName("responseData")
    private ResponseDataCategory responseDataCategory;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ResponseDataCategory getResponseDataCategory() {
        return responseDataCategory;
    }

    public void setResponseDataCategory(ResponseDataCategory responseDataCategory) {
        this.responseDataCategory = responseDataCategory;
    }
}
