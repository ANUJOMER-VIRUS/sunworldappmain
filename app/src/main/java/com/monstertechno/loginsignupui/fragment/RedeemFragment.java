package com.monstertechno.loginsignupui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monstertechno.loginsignupui.Adapter.reddemAdapter;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.CoinResponse;
import com.monstertechno.loginsignupui.modal.CoinResponseData;
import com.monstertechno.loginsignupui.modal.ReddemResponse;
import com.monstertechno.loginsignupui.modal.Redeems;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RedeemFragment extends Fragment {

RecyclerView recyclerViewRedeem;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView cointv;
    String userid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_redeem,container,false);
cointv=view.findViewById(R.id.coinsuser);
        sharedPreferences =this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
       userid=sharedPreferences.getString("userid","notfound");
RetrofitAPI retrofitAPI2=RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        CoinResponse coinResponse=new CoinResponse(userid);
Call<CoinResponse> coinResponseCall=retrofitAPI2.getcoin(coinResponse);
coinResponseCall.enqueue(new Callback<CoinResponse>() {
    @Override
    public void onResponse(Call<CoinResponse> call, Response<CoinResponse> response) {
        if(response.body().getStatus()){
            String coins=response.body().getResponseData().getCoins().toString();
            cointv.setText(coins);
        }

    }

    @Override
    public void onFailure(Call<CoinResponse> call, Throwable t) {

    }
});







        List<Redeems> list=new ArrayList<>();
   recyclerViewRedeem=view.findViewById(R.id.recyclerviewredeem);
        recyclerViewRedeem.setHasFixedSize(true);
        recyclerViewRedeem.setLayoutManager(new GridLayoutManager(getActivity(),2));
        reddemAdapter reddemAdapter=new reddemAdapter(list,getActivity());
        recyclerViewRedeem.setAdapter(reddemAdapter);

        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);

        Call<ReddemResponse> responseCall=retrofitAPI.getRedeemData(new ReddemResponse(userid));
        responseCall.enqueue(new Callback<ReddemResponse>() {
            @Override
            public void onResponse(Call<ReddemResponse> call, Response<ReddemResponse> response) {

                if(response.body().getStatus()){
                    if(response.body().getResponseData().getRedeemsList()!=null){
                    list.addAll(response.body().getResponseData().getRedeemsList());
                    reddemAdapter.notifyDataSetChanged();}


                }


            }

            @Override
            public void onFailure(Call<ReddemResponse> call, Throwable t) {

            }
        });


        return view;
    }
}