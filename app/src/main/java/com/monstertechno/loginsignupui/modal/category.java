package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class category {
    @SerializedName("id")
    private String id;
    @SerializedName("category_title")
    private String categoryTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
