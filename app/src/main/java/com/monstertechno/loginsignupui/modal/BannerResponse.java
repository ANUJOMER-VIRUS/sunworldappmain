package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BannerResponse {
    @SerializedName("apiName")
    @Expose
    private String apiName;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("responseCode")
    @Expose
    private Integer responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseData")
    @Expose
    private BannerResponseData responseData;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public BannerResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(BannerResponseData responseData) {
        this.responseData = responseData;
    }

}
