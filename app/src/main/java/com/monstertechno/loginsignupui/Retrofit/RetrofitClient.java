package com.monstertechno.loginsignupui.Retrofit;

import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private  static Retrofit retrofit=null;
private static RetrofitAPI retrofitAPI=null;
    private static final String BaseUrl="https://askinnovations.co.in/sunworld/api/";
            public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

            }


}

