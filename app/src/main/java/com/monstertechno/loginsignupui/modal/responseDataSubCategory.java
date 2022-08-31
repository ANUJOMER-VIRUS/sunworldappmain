package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class responseDataSubCategory {
    @SerializedName("subcategoriesList")
    private ArrayList<subcategorylist> subcategorylists=null;

    public ArrayList<subcategorylist> getSubcategorylists() {
        return subcategorylists;
    }

    public void setSubcategorylists(ArrayList<subcategorylist> subcategorylists) {
        this.subcategorylists = subcategorylists;
    }
}
