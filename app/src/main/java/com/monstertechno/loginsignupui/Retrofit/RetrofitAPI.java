package com.monstertechno.loginsignupui.Retrofit;

import com.monstertechno.loginsignupui.modal.Allbooking;
import com.monstertechno.loginsignupui.modal.Allbookingres;
import com.monstertechno.loginsignupui.modal.BannerResponse;
import com.monstertechno.loginsignupui.modal.CoinResponse;
import com.monstertechno.loginsignupui.modal.NewBookingRes;
import com.monstertechno.loginsignupui.modal.Occupationres;
import com.monstertechno.loginsignupui.modal.ReddemResponse;
import com.monstertechno.loginsignupui.modal.ResponseRv;
import com.monstertechno.loginsignupui.modal.SliderResponse;
import com.monstertechno.loginsignupui.modal.UserResponse;
import com.monstertechno.loginsignupui.modal.UserUpdate;
import com.monstertechno.loginsignupui.modal.getCategory;

import com.monstertechno.loginsignupui.modal.profile_modal;
import com.monstertechno.loginsignupui.modal.profileimgRes;
import com.monstertechno.loginsignupui.modal.redeemproductres;
import com.monstertechno.loginsignupui.modal.statusbooking;
import com.monstertechno.loginsignupui.modal.statusbookingdata;
import com.monstertechno.loginsignupui.modal.subcategoryData;
import com.monstertechno.loginsignupui.modal.userlogin_modal;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitAPI {
    @POST("user_register")
    Call<profile_modal> createPost(@Body profile_modal profile_modal);
@POST("user_login")
 Call<userlogin_modal> getuser(@Body userlogin_modal userlogin_modal);
@GET("getOccupation")
Call<Occupationres> getOccupation();
@POST("get_user_coins")
Call<CoinResponse>getcoin(@Body CoinResponse coinResponse);
@POST("getSubcategory")
Call<ResponseRv> getRv(@Body ResponseRv responseRv);
@GET("get_slider_list")
Call<SliderResponse> getSlider();
    @GET("get_banner_list")
    Call<BannerResponse> getbanner();
@POST("user_profile")
Call<UserResponse>getuserProfile(@Body UserResponse userResponse);
@POST("update_user_detail")
Call<UserUpdate>postuserupdate(@Body UserUpdate  userUpdate);
@Multipart
@POST("update_user_profile")
Call<profileimgRes> uploadImage(@Part MultipartBody.Part part, @Part("user_id") RequestBody userid);
@Multipart
@POST("New_booking")
Call<NewBookingRes> newBooking(@Part MultipartBody.Part Productphoto,@Part MultipartBody.Part Selfphoto,@Part("category_id") RequestBody catid,@Part("subcategory_id") RequestBody subcatid,@Part("name") RequestBody name,@Part("phone") RequestBody phone,@Part("address") RequestBody address,@Part("location") RequestBody location,@Part("user_id") RequestBody userid);
@POST("get_categories")
    Call<getCategory> getcategory(@Body getCategory getCategory);
@POST("get_subcategories")
    Call<subcategoryData> getsubcategory(@Body subcategoryData subcategoryData);
@POST("getRedeemsData")
    Call<ReddemResponse> getRedeemData(@Body ReddemResponse reddemResponse);
@POST("productRedeem")
    Call<redeemproductres> setredeemproduct(@Body redeemproductres redeemproductres);
@POST("get_user_booking")
    Call<Allbookingres> getallbooking(@Body Allbookingres allbooking);
@POST("get_userBookingstatus")
    Call<statusbooking> getstatusbook(@Body statusbookingdata statusbookingdata);
}
