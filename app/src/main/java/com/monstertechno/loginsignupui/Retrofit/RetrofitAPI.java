package com.monstertechno.loginsignupui.Retrofit;

import com.monstertechno.loginsignupui.modal.ResponseDataModal;
import com.monstertechno.loginsignupui.modal.profile_modal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("user_register")
    Call<profile_modal> createPost(@Body profile_modal profile_modal);
    @POST("user_login")
    Call<ResponseDataModal> getid(@Body ResponseDataModal responseDataModal);

}
