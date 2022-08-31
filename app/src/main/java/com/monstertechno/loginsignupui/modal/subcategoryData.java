package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class subcategoryData {
    @SerializedName("status")
    private Boolean status;
    @SerializedName("responseData")
    private responseDataSubCategory responseDataSubCategory;
    @SerializedName("category_id")
    private int category_id;

    public subcategoryData(int category_id) {
        this.category_id = category_id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public com.monstertechno.loginsignupui.modal.responseDataSubCategory getResponseDataSubCategory() {
        return responseDataSubCategory;
    }

    public void setResponseDataSubCategory(com.monstertechno.loginsignupui.modal.responseDataSubCategory responseDataSubCategory) {
        this.responseDataSubCategory = responseDataSubCategory;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
