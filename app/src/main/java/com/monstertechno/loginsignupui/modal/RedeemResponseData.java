package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedeemResponseData {
    @SerializedName("redeemsList")
    @Expose
    private List<Redeems> redeemsList = null;

    public List<Redeems> getRedeemsList() {
        return redeemsList;
    }

    public void setRedeemsList(List<Redeems> redeemsList) {
        this.redeemsList = redeemsList;
    }
}
