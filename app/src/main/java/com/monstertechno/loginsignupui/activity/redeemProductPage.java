package com.monstertechno.loginsignupui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.CoinResponse;
import com.monstertechno.loginsignupui.modal.redeemproductres;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class redeemProductPage extends AppCompatActivity {
TextView value;
int count=0;
    String product_coin;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
ImageView plus,minus;
TextView productc,needc,description;
String user_coins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_product_page);
        Intent intent=getIntent();
        String product_des=intent.getStringExtra("des");
        String product_id=intent.getStringExtra("product_id");
        String product_name=intent.getStringExtra("product_name");
        String product_url=intent.getStringExtra("product_url");
        product_coin=intent.getStringExtra("coin");
        productc=findViewById(R.id.productCoins);
        productc.setText(product_coin+" ");
         description=findViewById(R.id.description);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
       description.setText(Html.fromHtml(product_des, Html.FROM_HTML_MODE_COMPACT));
        }
        else {
            description.setText(Html.fromHtml(product_des));

        }

        sharedPreferences =this.getSharedPreferences("login", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String userid=sharedPreferences.getString("userid","notfound");;
        ImageView productiv=findViewById(R.id.imagerp);
        TextView producttv=findViewById(R.id.namerp);
        Picasso.get().load(product_url).into(productiv);
        producttv.setText(product_name);
        RetrofitAPI retrofitAPI2= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        CoinResponse coinResponse=new CoinResponse(userid);
        Call<CoinResponse> coinResponseCall=retrofitAPI2.getcoin(coinResponse);
        coinResponseCall.enqueue(new Callback<CoinResponse>() {
            @Override
            public void onResponse(Call<CoinResponse> call, Response<CoinResponse> response) {
                if(response.body().getStatus()){
                    user_coins=response.body().getResponseData().getCoins().toString();

 

                }

            }

            @Override
            public void onFailure(Call<CoinResponse> call, Throwable t) {

            }
        });

needc=findViewById(R.id.totalcoins);
        value=findViewById(R.id.in_derp);
plus=findViewById(R.id.plusrp);
minus=findViewById(R.id.minusrp);





        plus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int feasiblity=0;


        String currentvalue = value.getText().toString();
if(Integer.valueOf(user_coins) - Integer.valueOf(product_coin)*Integer.valueOf(value.getText().toString())>=Integer.valueOf(product_coin)) {

    feasiblity = Integer.valueOf(user_coins) - Integer.valueOf(product_coin) * Integer.valueOf(value.getText().toString());
}

        int value1 = Integer.parseInt(currentvalue);

        if (value1 > feasiblity -1) {
            Toast.makeText(redeemProductPage.this, "Not possible", Toast.LENGTH_SHORT).show();
        } else {

            value1++;
            int totalvalue = Integer.parseInt(product_coin) * value1;
            needc.setText("" + (totalvalue)+" ");
            value.setText(String.valueOf(value1));
        }
    }
});
minus.setOnClickListener((new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String currentvalue=value.getText().toString();
        int value1=Integer.parseInt(currentvalue);
        if(value1<=0){
            value1=0;
        }
        else {
            value1--;
        }
        int totalvalue=Integer.parseInt(product_coin)*value1;
        needc.setText(""+(totalvalue)+" ");
        value.setText(String.valueOf(value1));
    }
}));


        Button submit=findViewById(R.id.submitredeem);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //
                if(value.getText().toString().equals("0")){
                    Toast.makeText(redeemProductPage.this, "Please select more than 1", Toast.LENGTH_SHORT).show();
                }
                else {
                String numberofproduct=value.getText().toString();

                sendtodb(userid,product_id,numberofproduct);
                finish();
            }}
        });

    }

    private void sendtodb(String userid, String product_id, String numberofproduct) {
        RetrofitAPI retrofitAPI2= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        redeemproductres redeemproductres=new redeemproductres(userid,product_id,numberofproduct);
        Call<redeemproductres> redeemproductresCall=retrofitAPI2.setredeemproduct(redeemproductres);

        redeemproductresCall.enqueue(new Callback<com.monstertechno.loginsignupui.modal.redeemproductres>() {
            @Override
            public void onResponse(Call<com.monstertechno.loginsignupui.modal.redeemproductres> call, Response<com.monstertechno.loginsignupui.modal.redeemproductres> response) {
                if(response.body().getStatus()){
                    String resm=response.body().getResponseMessage();
                    Toast.makeText(redeemProductPage.this, resm, Toast.LENGTH_SHORT).show();
                }
                else{
                    String resm=response.body().getResponseMessage();
                    Toast.makeText(redeemProductPage.this, resm, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<com.monstertechno.loginsignupui.modal.redeemproductres> call, Throwable t) {
                Toast.makeText(redeemProductPage.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}