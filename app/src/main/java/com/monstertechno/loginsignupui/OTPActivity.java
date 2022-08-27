package com.monstertechno.loginsignupui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class  OTPActivity extends AppCompatActivity {

    Button button;
TextView textView;
private String  verifyOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_otpactivity);
        button=(Button)findViewById(R.id.btnverify);
        textView= (TextView) findViewById(R.id.otpnumber);
        Intent intent=getIntent();
        String mobile=intent.getStringExtra("mobile");

    verifyOtp=intent.getStringExtra("verificationOtp");
        textView.setText("Enter one time password sent On "+mobile);

       EditText pinView=(EditText)findViewById(R.id.pinview);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(OTPActivity.this,SignupActivity.class);

               // startActivity(intent);
                String otp=pinView.getText().toString();
               // Toast.makeText(OTPActivity.this,verifyOtp,Toast.LENGTH_SHORT).show();
                if(verifyOtp!=null) {
                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(verifyOtp,otp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent1=new Intent(getApplicationContext(),HomeActivity.class);
                                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent1);
                                }
                                else {
                                    Toast.makeText(OTPActivity.this,"enter correct otp",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                }

            }
        });
    }


}
