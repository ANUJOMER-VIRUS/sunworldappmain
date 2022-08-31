package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class subcategorylist {
    @SerializedName("id")
    private String id;
    @SerializedName("subcategory_title")
   private String subcategory_title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcategory_title() {
        return subcategory_title;
    }

    public void setSubcategory_title(String subcategory_title) {
        this.subcategory_title = subcategory_title;
    }
}
