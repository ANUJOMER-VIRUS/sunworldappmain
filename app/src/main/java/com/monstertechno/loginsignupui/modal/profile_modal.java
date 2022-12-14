package com.monstertechno.loginsignupui.modal;

import com.google.gson.annotations.SerializedName;

public class profile_modal {
    @SerializedName("full_name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String mobilenumber;
    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("occupation_id")
    private String occupation;
    @SerializedName("status")
    private Boolean status;
    @SerializedName("responseMessage")
    private String responsemessage;
@SerializedName("responseCode")
private String responsecode;
@SerializedName("user_id")
private String user_id;

    public profile_modal(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public profile_modal(String name, String email, String mobilenumber, String address, String city, String occupation) {
        this.name = name;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.address = address;
        this.city = city;
        this.occupation = occupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getResponsemessage() {
        return responsemessage;
    }

    public void setResponsemessage(String responsemessage) {
        this.responsemessage = responsemessage;
    }
}


