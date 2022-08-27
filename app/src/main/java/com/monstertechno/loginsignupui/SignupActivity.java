package com.monstertechno.loginsignupui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity {
EditText name,email,mobilenumber,Address,city,Occupation;
    FloatingActionButton floatingActionButton;
    private FirebaseAuth mAuth;
    private String verifcationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.namesup);
        email = findViewById(R.id.emailsup);
        mobilenumber = findViewById(R.id.mobilesup);
        Address = findViewById(R.id.addresssup);
        city = findViewById(R.id.citysup);
        Occupation = findViewById(R.id.occupationsup);

        mAuth = FirebaseAuth.getInstance();


        floatingActionButton = findViewById(R.id.floatingbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mobilenumber.getText().toString()) && TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(email.getText().toString()) && TextUtils.isEmpty(Address.getText().toString()) && TextUtils.isEmpty(city.getText().toString()) && TextUtils.isEmpty(Occupation.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "enter all field", Toast.LENGTH_SHORT).show();

                } else if (mobilenumber.getText().toString().length() != 10) {
                    Toast.makeText(getApplicationContext(), "enter 10 digit mobile number", Toast.LENGTH_SHORT).show();

                } else {
                    profile_modal profile_modal=new profile_modal(name.getText().toString(),email.getText().toString(),mobilenumber.getText().toString(),Address.getText().toString(),city.getText().toString(),Occupation.getText().toString());
                    sendDatatoDb(profile_modal);
                    String phone = "+91" + mobilenumber.getText().toString();
                    verifyNumber(phone);
                }

            }
        });
    }


    private void verifyNumber(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                SignupActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Toast.makeText(getApplicationContext(),"verification of mobile done",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(),"verification of mobile failed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        Intent intent = new Intent(SignupActivity.this,OTPActivity.class);
                        intent.putExtra("mobile",  phone);
                        intent.putExtra("verificationOtp",s);

                        startActivity(intent);
                    }
                }
        );
    }

    private void sendDatatoDb(profile_modal profile_modal) {
RetrofitAPI retrofitAPI=RetrofitClient.getRetrofit().create(RetrofitAPI.class);
Call<profile_modal> call=retrofitAPI.createPost(profile_modal);
call.enqueue(new Callback<com.monstertechno.loginsignupui.profile_modal>() {
    @Override
    public void onResponse(Call<com.monstertechno.loginsignupui.profile_modal> call, Response<com.monstertechno.loginsignupui.profile_modal> response) {
        if(response.body().getStatus()){
            Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<com.monstertechno.loginsignupui.profile_modal> call, Throwable t) {
        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

    }
});

    }
}
