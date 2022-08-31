package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class getCategory {
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
