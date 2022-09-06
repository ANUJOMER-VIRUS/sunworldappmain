package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SliderResponseData {

    @SerializedName("sliderList")
    @Expose
    private List<Slider> sliderList = null;

    public List<Slider> getSliderList() {
        return sliderList;
    }

    public void setSliderList(List<Slider> sliderList) {
        this.sliderList = sliderList;
    }

}
