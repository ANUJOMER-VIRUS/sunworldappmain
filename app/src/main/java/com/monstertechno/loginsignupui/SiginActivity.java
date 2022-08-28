package com.monstertechno.loginsignupui;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SiginActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    EditText mobile;
    private FirebaseAuth mAuth;
    private String verifcationId;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_sigin);

        mAuth=FirebaseAuth.getInstance();
        mobile=findViewById(R.id.mobilenumber);

        floatingActionButton=findViewById(R.id.floatingbtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(TextUtils.isEmpty(mobile.getText().toString())){
                 Toast.makeText((SiginActivity.this),"enter mobile number",Toast.LENGTH_SHORT).show();

             }
             else if(mobile.getText().toString().length()!=10){
                 Toast.makeText((SiginActivity.this),"enter 10 digit mobile number",Toast.LENGTH_SHORT).show();

             }
             else{
                 String phone="+91"+mobile.getText().toString();
                 id(mobile.getText().toString());
                 Toast.makeText(getApplicationContext(),userid,Toast.LENGTH_SHORT);
                 PhoneAuthProvider.getInstance().verifyPhoneNumber(
                         phone,
                         60,
                         TimeUnit.SECONDS,
                         SiginActivity.this,
                         new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                             @Override
                             public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                 Toast.makeText(SiginActivity.this,"verification of mobile done",Toast.LENGTH_SHORT).show();

                             }

                             @Override
                             public void onVerificationFailed(@NonNull FirebaseException e) {
                                 Toast.makeText(SiginActivity.this,"verification of mobile failed",Toast.LENGTH_SHORT).show();

                             }

                             @Override
                             public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                 Intent intent=new Intent(SiginActivity.this,OTPActivity.class);
                                 intent.putExtra("mobile",phone);
                                 intent.putExtra("verificationOtp",s);
                                 startActivity(intent);
                             }
                         }
                 );


             }



            }
        });
        TextView textView =findViewById(R.id.sign_uptv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





               startActivity(new Intent(getApplicationContext(),SignupActivity.class));
            }
        });
    }




        private void id(String phone){

    ResponseDataModal responseDataModal=new ResponseDataModal(phone);
    RetrofitAPI retrofitAPI=RetrofitClient.getRetrofit().create(RetrofitAPI.class);
    Call<ResponseDataModal>call =retrofitAPI.getid(responseDataModal);
    call.enqueue(new Callback<ResponseDataModal>() {
        @Override
        public void onResponse(Call<ResponseDataModal> call, Response<ResponseDataModal> response) {
            if(response.isSuccessful()){
           String ui=(String) response.body().getUser_idmodal().getUser_id();
             userid=ui;
            }
        }

        @Override
        public void onFailure(Call<ResponseDataModal> call, Throwable t) {

        }
    });








}


}