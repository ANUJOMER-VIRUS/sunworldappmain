package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusBookingResponseData {

    @SerializedName("bookingList")
    @Expose
    private List<Allbooking> bookingList = null;

    public List<Allbooking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Allbooking> bookingList) {
        this.bookingList = bookingList;
    }

}
