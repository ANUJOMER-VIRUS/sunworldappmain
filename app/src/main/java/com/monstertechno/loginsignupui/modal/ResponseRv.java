package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseRv {
    @SerializedName("user_id")
    @Expose
    String userid;

    public ResponseRv(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

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
    private ResponseDataRv responseData;

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

    public ResponseDataRv getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataRv responseData) {
        this.responseData = responseData;
    }
}
