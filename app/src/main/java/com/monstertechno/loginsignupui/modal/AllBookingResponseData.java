package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllBookingResponseData {
    @SerializedName("all_bookingList")
    @Expose
    private List<Allbooking> allBookingList = null;

    public List<Allbooking> getAllBookingList() {
        return allBookingList;
    }

    public void setAllBookingList(List<Allbooking> allBookingList) {
        this.allBookingList = allBookingList;
    }
}
