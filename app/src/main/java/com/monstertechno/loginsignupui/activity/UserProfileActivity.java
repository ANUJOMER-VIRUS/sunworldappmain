package com.monstertechno.loginsignupui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.databinding.ActivityUserProfileBinding;
import com.monstertechno.loginsignupui.fragment.ProfileFragment;
import com.monstertechno.loginsignupui.modal.RealPathUtil;
import com.monstertechno.loginsignupui.modal.ResponseDataUser;
import com.monstertechno.loginsignupui.modal.UserProfile;
import com.monstertechno.loginsignupui.modal.UserResponse;
import com.monstertechno.loginsignupui.modal.UserUpdate;
import com.monstertechno.loginsignupui.modal.profileimgRes;
import com.monstertechno.loginsignupui.pathUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    ImageView imageback, profilepic;
   // ActivityUserProfileBinding binding;
    TextInputEditText name, mobile, email, address, city, occupation;
    Button button, upload;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private final int PICK_IMAGE_REQUEST = 22;
    String path,userId;
    private Uri filePath;
    private final int Permission_code = 22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
      //  binding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        profilepic = findViewById(R.id.profilepic);
        name = findViewById(R.id.nameprofile);
        mobile = findViewById(R.id.mobileprofile);
        email = findViewById(R.id.emailprofile);
        address = findViewById(R.id.Addressprofile);
        city = findViewById(R.id.LocationProfile);

        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        button = findViewById(R.id.submitprofile);
        userId = sharedPreferences.getString("userid", "not_found");
        //Toast.makeText(getApplicationContext(), userId, Toast.LENGTH_SHORT).show();
        UserResponse userResponse = new UserResponse(userId);
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<UserResponse> call = retrofitAPI.getuserProfile(userResponse);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body().getStatus()) {
                    ResponseDataUser responseDataUser = response.body().getResponseData();
                    List<UserProfile> userProfiles = responseDataUser.getUserProfile();
                    UserProfile userProfile;
                    userProfile = userProfiles.get(0);
                    name.setText(userProfile.getFullName());
                    mobile.setText(userProfile.getPhone());


                    email.setText(userProfile.getEmail());
                    address.setText(userProfile.getAddress());
                    city.setText(userProfile.getLocation());
                    Picasso.get().load(userProfile.getImage()).into(profilepic);

                 //   Toast.makeText(getApplicationContext(), userProfile.getPhone(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
        //UserUpdate userUpdate=new UserUpdate(userId,name.getText().toString(),address.getText().toString(),city.getText().toString(),email.getText().toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString())&&TextUtils.isEmpty(address.getText().toString())&&TextUtils.isEmpty(city.getText().toString())&&TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(getApplicationContext(),"please enter all field",Toast.LENGTH_SHORT).show();
                }else {
                    UserUpdate userUpdate = new UserUpdate(userId, name.getText().toString(), address.getText().toString(), city.getText().toString(), email.getText().toString());

                    RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                    Call<UserUpdate> userUpdateCall = retrofitAPI.postuserupdate(userUpdate);
                    userUpdateCall.enqueue(new Callback<UserUpdate>() {
                        @Override
                        public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                            if (response.body().getStatus()) {
                                Toast.makeText(getApplicationContext(), "user data updated", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(), "user data  not updated", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<UserUpdate> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                if(filePath!=null){
                    try {
                        adddata(userId,filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==
                    PackageManager.PERMISSION_DENIED){
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                       requestPermissions(permissions,Permission_code);
                    }
                    else{
                        PickImage();
                    }
                }

            }
        });



    }

    private void PickImage() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode){
           case Permission_code:{
               if(grantResults.length>0&&grantResults[0]==
               PackageManager.PERMISSION_GRANTED){
                   PickImage();
               }
               else{
                   Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
               }
           }
       }
    }

    private void adddata(String userId, Uri path) throws IOException {

        if (path != null) {

            RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
            File file = getFile(getApplicationContext(),path);

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            RequestBody user_id = RequestBody.create(MediaType.parse("multipart/form-data"), userId);
            Call<profileimgRes> call = retrofitAPI.uploadImage(body, user_id);
            call.enqueue(new Callback<profileimgRes>() {
                @Override
                public void onResponse(Call<profileimgRes> call, Response<profileimgRes> response) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), "uploaded", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "not Added", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<profileimgRes> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "select Image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode,
               resultCode,
               data);

        if (resultCode ==RESULT_OK) {
            if (resultCode == RESULT_OK) {
                if(requestCode==10){
                    Uri selectedImageUri = data.getData();
                    filePath=selectedImageUri;
                    if(null != selectedImageUri){
                        path=RealPathUtil.getRealPath(UserProfileActivity.this,selectedImageUri);
                        profilepic.setImageURI(filePath);


                    }

                }
            }

        }

    }



    public static File getFile(Context context, Uri uri) throws IOException {
        File destinationFilename = new File(context.getFilesDir().getPath() + File.separatorChar + queryName(context, uri));
        try (InputStream ins = context.getContentResolver().openInputStream(uri)) {
            createFileFromStream(ins, destinationFilename);
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
        return destinationFilename;
    }

    public static void createFileFromStream(InputStream ins, File destination) {
        try (OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = ins.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception ex) {
            Log.e("Save File", ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static String queryName(Context context, Uri uri) {
        Cursor returnCursor =
                context.getContentResolver().query(uri, null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();
        return name;
    }

}


