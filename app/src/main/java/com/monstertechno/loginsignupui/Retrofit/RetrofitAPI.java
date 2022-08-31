package com.monstertechno.loginsignupui.Retrofit;

import com.monstertechno.loginsignupui.modal.getCategory;

import com.monstertechno.loginsignupui.modal.profile_modal;
import com.monstertechno.loginsignupui.modal.subcategoryData;
import com.monstertechno.loginsignupui.modal.userlogin_modal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("user_register")
    Call<profile_modal> createPost(@Body profile_modal profile_modal);
@POST("user_login")
 Call<userlogin_modal> getuser(@Body userlogin_modal userlogin_modal);


@GET("get_categories")
    Call<getCategory> getcategory();
@POST("get_subcategories")
    Call<subcategoryData> getsubcategory(@Body subcategoryData subcategoryData);

}
