package com.monstertechno.loginsignupui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.monstertechno.loginsignupui.Adapter.BannerAdapter;
import com.monstertechno.loginsignupui.Adapter.ItemAdapter;
import com.monstertechno.loginsignupui.Adapter.SliderAdapter;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.Banner;
import com.monstertechno.loginsignupui.modal.BannerResponse;
import com.monstertechno.loginsignupui.modal.ResponseRv;
import com.monstertechno.loginsignupui.modal.Slider;
import com.monstertechno.loginsignupui.modal.SliderResponse;
import com.monstertechno.loginsignupui.modal.SubCategory;
import com.smarteist.autoimageslider.SliderView;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
SliderView sliderView,sliderView2;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler);


ArrayList<Slider> sliderArrayList=new ArrayList<>();
sliderView=view.findViewById(R.id.imageSlidertop);
sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
sliderView.setScrollTimeInSec(10);
sliderView.setAutoCycle(true);
sliderView.startAutoCycle();
RetrofitAPI retrofitAPI2=RetrofitClient.getRetrofit().create(RetrofitAPI.class);
Call<SliderResponse> sliderResponseCall=retrofitAPI2.getSlider();
sliderResponseCall.enqueue(new Callback<SliderResponse>() {
    @Override
    public void onResponse(Call<SliderResponse> call, Response<SliderResponse> response) {

     List<Slider> sliderList=new ArrayList<>();
     if(response.body().getStatus()){
         sliderList=response.body().getResponseData().getSliderList();
         sliderView.setSliderAdapter(new SliderAdapter(getContext(),sliderList));

     }
     else {
     }
    }

    @Override
    public void onFailure(Call<SliderResponse> call, Throwable t) {
Toast.makeText(getActivity(),"failed",Toast.LENGTH_SHORT).show();
    }
});

      List<SubCategory> list = new ArrayList<>();
        sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String userid=sharedPreferences.getString("userid","notfound");

        ResponseRv responseRv=new ResponseRv(userid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemAdapter adapter=new ItemAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

            RetrofitAPI retrofitAPI=RetrofitClient.getRetrofit().create(RetrofitAPI.class);
            Call<ResponseRv> call=retrofitAPI.getRv(responseRv);
            call.enqueue(new Callback<ResponseRv>() {
                @Override
                public void onResponse(Call<ResponseRv> call, Response<ResponseRv> response) {
                   List<SubCategory> list1=new ArrayList<>();
                    if(response.body().getStatus()){
                       list.addAll(response.body().getResponseData().getSubcategoryList());
                       adapter.notifyDataSetChanged();
                    }
                    else{

                    }
                }

                @Override
                public void onFailure(Call<ResponseRv> call, Throwable t) {
list.add(new SubCategory("sun","sun","sun","sun","sun"));
adapter.notifyDataSetChanged();
                }
            });


            sliderView2=view.findViewById(R.id.imageBannerbottom);
        sliderView2.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView2.setScrollTimeInSec(10);
        sliderView2.setAutoCycle(true);
        sliderView2.startAutoCycle();
        RetrofitAPI retrofitAPI3=RetrofitClient.getRetrofit().create(RetrofitAPI.class);
Call<BannerResponse>bannerResponseCall=retrofitAPI3.getbanner();
bannerResponseCall.enqueue(new Callback<BannerResponse>() {
    @Override
    public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
        List<Banner> bannerList=new ArrayList<>();
        if(response.body().getStatus()){
            bannerList=response.body().getResponseData().getBannerList();
            sliderView2.setSliderAdapter(new BannerAdapter(getContext(),bannerList));
        }
    }

    @Override
    public void onFailure(Call<BannerResponse> call, Throwable t) {

    }
});
        return view;


    }
}

