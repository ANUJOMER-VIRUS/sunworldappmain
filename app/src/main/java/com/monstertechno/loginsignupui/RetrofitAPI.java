package com.monstertechno.loginsignupui;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("user_register")
    Call<profile_modal> createPost(@Body profile_modal profile_modal);




}
