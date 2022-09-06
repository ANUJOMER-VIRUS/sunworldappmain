package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDataRv {

    @SerializedName("subcategoryList")
    @Expose
    private List<SubCategory> subcategoryList = null;

    public List<SubCategory> getSubcategoryList() {
        return subcategoryList;
    }

    public void setSubcategoryList(List<SubCategory> subcategoryList) {
        this.subcategoryList = subcategoryList;
    }
}
