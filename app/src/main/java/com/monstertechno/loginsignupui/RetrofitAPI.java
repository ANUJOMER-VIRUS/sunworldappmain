package com.monstertechno.loginsignupui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @POST("user_register")
    Call<profile_modal> createPost(@Body profile_modal profile_modal);
    @POST("user_login")
    Call<ResponseDataModal> getid(@Body ResponseDataModal responseDataModal);

}
