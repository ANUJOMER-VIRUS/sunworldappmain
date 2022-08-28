package com.monstertechno.loginsignupui;

import com.google.gson.annotations.SerializedName;
import com.monstertechno.loginsignupui.user_idmodal;

public class ResponseDataModal {
  @SerializedName("mobile_number")
    String mobilenumber;
  @SerializedName("responseData")
  user_idmodal user_idmodal;
  @SerializedName("responseMessage")
  String res;

  public ResponseDataModal(String mobilenumber) {
    this.mobilenumber = mobilenumber;
  }

  public String getMobilenumber() {
    return mobilenumber;
  }

  public com.monstertechno.loginsignupui.user_idmodal getUser_idmodal() {
    return user_idmodal;
  }

  public String getRes() {
    return res;
  }
}
