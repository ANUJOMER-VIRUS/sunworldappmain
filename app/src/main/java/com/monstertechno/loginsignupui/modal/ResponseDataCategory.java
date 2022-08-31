package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseDataCategory {
    @SerializedName("categoriesList")
    private ArrayList<category> categoryArrayList=null;

    public ArrayList<category> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(ArrayList<category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }
}
