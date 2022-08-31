package com.monstertechno.loginsignupui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.profile_modal;
import com.monstertechno.loginsignupui.modal.userlogin_modal;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiginActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    EditText mobile;
 private SharedPreferences sharedPreferences;
private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_sigin);
        sharedPreferences =this.getSharedPreferences("login",MODE_PRIVATE);
editor=sharedPreferences.edit();
if(sharedPreferences.getString("islogin","false").equals("yes")){
    openHome();
}



        mobile = findViewById(R.id.mobilenumber);


        floatingActionButton = findViewById(R.id.floatingbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
String mob=mobile.getText().toString();
dologin(mob);

                                                    }
                                                }
        );
    }


    private void dologin(String mob){
        sharedPreferences =this.getSharedPreferences("login",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        userlogin_modal userlogin_modal=new userlogin_modal(mob);
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<userlogin_modal> call =retrofitAPI.getuser(userlogin_modal);
        call.enqueue(new Callback<com.monstertechno.loginsignupui.modal.userlogin_modal>() {
            @Override
            public void onResponse(Call<com.monstertechno.loginsignupui.modal.userlogin_modal> call, Response<com.monstertechno.loginsignupui.modal.userlogin_modal> response) {
                if(response.body().isNewuser()){

                    startActivity(new Intent(getApplicationContext(),SignupActivity.class));
                    finish();

                }
                else {
                    if(response.body().isStatus()){
                     //   Toast.makeText(getApplicationContext(),response.body().getUserid(),Toast.LENGTH_SHORT).show();
                        editor.putString("islogin","yes");
                        editor.putString("userid",response.body().getUserid());
                        editor.commit();
                       openHome();

                    }
                }
            }

            @Override
            public void onFailure(Call<com.monstertechno.loginsignupui.modal.userlogin_modal> call, Throwable t) {

            }
        });


    }

    private void openHome() {

startActivity(new Intent(getApplicationContext(),HomeActivity.class));
finish();
    }
}








