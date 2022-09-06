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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.Occupation;
import com.monstertechno.loginsignupui.modal.Occupationres;
import com.monstertechno.loginsignupui.modal.profile_modal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText name,email,mobilenumber,Address,city;

    FloatingActionButton floatingActionButton;
    private FirebaseAuth mAuth;
    Spinner occupation;
    private String verifcationId;
    List<String> occupationdata;
    String Occupationname;
private SharedPreferences sharedPreferences;
private SharedPreferences.Editor editor;
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

        occupation =findViewById(R.id.occupationsup);
        occupation.setOnItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();

        occupationdata=getoccupation();

        floatingActionButton = findViewById(R.id.floatingbutton);


    }

    private List<String> getoccupation() {
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        List<String > occupationlist=new ArrayList<>();
        List<String> occupationid=new ArrayList<>();
        Call<Occupationres> occupationresCall=retrofitAPI.getOccupation();
        occupationresCall.enqueue(new Callback<Occupationres>() {
            @Override
            public void onResponse(Call<Occupationres> call, Response<Occupationres> response) {
                List<Occupation> occupationList;
                if(response.body().getStatus()){
                    occupationList=response.body().getResponseData().getOccupationList();
                    for (int i = 0; i < occupationList.size(); i++) {
                        Occupation occupation1=occupationList.get(i);
                        occupationlist.add(occupation1.getName());
occupationid.add(occupation1.getId());
                    }
                    ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,occupationlist);
                    adapter.setDropDownViewResource( android.R.layout
                            .simple_spinner_dropdown_item);
                    occupation.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Occupationres> call, Throwable t) {
                occupationlist.add("fail to get occupation");
            }
        });
        return occupationid;
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
                        Intent intent = new Intent(SignupActivity.this, OTPActivity.class);
                        intent.putExtra("mobile",  phone);
                        intent.putExtra("verificationOtp",s);

                        startActivity(intent);
                    }
                }
        );
    }

    private void sendDatatoDb(profile_modal profile_modal) {
        sharedPreferences =this.getSharedPreferences("login",MODE_PRIVATE);
        editor=sharedPreferences.edit();
RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
Call<profile_modal> call=retrofitAPI.createPost(profile_modal);
call.enqueue(new Callback<com.monstertechno.loginsignupui.modal.profile_modal>() {
    @Override
    public void onResponse(Call<com.monstertechno.loginsignupui.modal.profile_modal> call, Response<com.monstertechno.loginsignupui.modal.profile_modal> response) {
        if(response.body().getStatus()){
            editor.putString("userid",response.body().getUser_id());
            editor.putString("occupation",profile_modal.getOccupation());
            editor.commit();

            String phone = "+91" + mobilenumber.getText().toString();
            verifyNumber(phone);
        }
        else {
            gotohome();
            Toast.makeText(getApplicationContext(),response.body().getResponsemessage(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onFailure(Call<com.monstertechno.loginsignupui.modal.profile_modal> call, Throwable t) {
        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

    }
});

    }
    private void gotohome(){
        startActivity(new Intent(getApplicationContext(),SiginActivity.class));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

Occupationname=occupationdata.get(i);
submitdata(Occupationname);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public  void submitdata(String occupationname){

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mobilenumber.getText().toString()) && TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(email.getText().toString()) && TextUtils.isEmpty(Address.getText().toString()) && TextUtils.isEmpty(city.getText().toString())  ){
                    Toast.makeText(getApplicationContext(), "enter all field", Toast.LENGTH_SHORT).show();

                } else if (mobilenumber.getText().toString().length() != 10) {
                    Toast.makeText(getApplicationContext(), "enter 10 digit mobile number", Toast.LENGTH_SHORT).show();

                } else {
                    profile_modal profile_modal=new profile_modal(name.getText().toString(),email.getText().toString(),mobilenumber.getText().toString(),Address.getText().toString(),city.getText().toString(),occupationname);

                  sendDatatoDb(profile_modal);

                }

            }
        });
    }
}
