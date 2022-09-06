package com.monstertechno.loginsignupui.activity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.textfield.TextInputEditText;
import com.monstertechno.loginsignupui.Adapter.categoryspinnerAdapter;
import com.monstertechno.loginsignupui.Adapter.subcategorySpinnerAdapter;
import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.modal.NewBookingRes;
import com.monstertechno.loginsignupui.modal.RealPathUtil;
import com.monstertechno.loginsignupui.modal.category;
import com.monstertechno.loginsignupui.modal.getCategory;
import com.monstertechno.loginsignupui.modal.subcategoryData;
import com.monstertechno.loginsignupui.modal.subcategorylist;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Newbookingactvity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    LocationManager locationManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final int REQUEST_LOCATION = 1;
    Spinner category,subcategory;
    TextInputEditText name,phone,email,address,location;
   ImageView shelfphoto,Productphoto;
   Button Submit;
File selectedImageUri1;
File selectedImageUri2;

ArrayList<com.monstertechno.loginsignupui.modal.category> categoryArrayList;
ArrayList<subcategorylist> subcategorylistArrayList;
categoryspinnerAdapter categoryspinnerAdapter;
subcategorySpinnerAdapter subcategorySpinnerAdapter;
    int SELECT_PICTURE = 1;
    int second_picture=2;
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE1 = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newbookingactvity);
        category=findViewById(R.id.categotyspinner);
        subcategory=findViewById(R.id.subcategorySpinner);
        name=findViewById(R.id.namenb);
        phone=findViewById(R.id.phonenb);
        ActivityCompat.requestPermissions(this,new String[]{
                        ACCESS_FINE_LOCATION}
                ,REQUEST_LOCATION);
        address=findViewById(R.id.addressnb);

        shelfphoto=findViewById(R.id.selfimage);
        Productphoto=findViewById(R.id.productimage);
        Submit=findViewById(R.id.submitnb);

        sharedPreferences =this.getSharedPreferences("login", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        String userid=sharedPreferences.getString("userid","notfound");
 getcategory(userid);

        shelfphoto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                CAMERA_REQUEST_CODE);
                    } else {
                        pickshelfimage();
                    }

                }
            }
        });
Productphoto.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        CAMERA_REQUEST_CODE1);
            } else {
                pickproductimage();
            }

        }
    }
});
        final String[] id = {""};
       category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               com.monstertechno.loginsignupui.modal.category category1= (com.monstertechno.loginsignupui.modal.category) adapterView.getItemAtPosition(i);
               id[0] =category1.getId();
               getSubcategory(Integer.parseInt(id[0]));
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
        final String[] subid = {""};
       subcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               subcategorylist subcategorylist=(com.monstertechno.loginsignupui.modal.subcategorylist) adapterView.getItemAtPosition(i);
               subid[0] =subcategorylist.getId();

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);



Submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
    OnGps();
}
else {


    if (selectedImageUri1 == null && selectedImageUri2 == null) {
        Toast.makeText(getApplicationContext(), "please Select picture", Toast.LENGTH_SHORT).show();
    } else {

        int cati = Integer.parseInt(id[0]);

        int subcati = Integer.parseInt(subid[0]);
        String namei = name.getText().toString();
        String phonei = phone.getText().toString();

        String Addressi = address.getText().toString();
        String locationi = getLocati();
        File selffile = selectedImageUri2, productfile = selectedImageUri1;
        Toast.makeText(Newbookingactvity.this, locationi, Toast.LENGTH_SHORT).show();

        if (cati < 0 && subcati < 0) {
            Toast.makeText(getApplicationContext(), "Select category and subcategory", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(namei) && TextUtils.isEmpty(phonei) && TextUtils.isEmpty(Addressi) && TextUtils.isEmpty(locationi)) {
            Toast.makeText(getApplicationContext(), "enter all field", Toast.LENGTH_SHORT).show();

        } else {
            RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
            RequestBody requestProduct = RequestBody.create(MediaType.parse("multipart/form-data"), productfile);
            MultipartBody.Part ProducBody = MultipartBody.Part.createFormData("proof_of_installation", productfile.getName(), requestProduct);
            RequestBody requestSelf = RequestBody.create(MediaType.parse("multipart/form-data"), selffile);
            MultipartBody.Part SelfBody = MultipartBody.Part.createFormData("proof_of_delivery", selffile.getName(), requestSelf);
            RequestBody namerb = RequestBody.create(MediaType.parse("multipart/form-data"), namei);
            RequestBody phonerb = RequestBody.create(MediaType.parse("multipart/form-data"), phonei);
            RequestBody uid = RequestBody.create(MediaType.parse("multipart/form-data"), userid);
            RequestBody addreesrb = RequestBody.create(MediaType.parse("multipart/form-data"), Addressi);
            RequestBody locationrb = RequestBody.create(MediaType.parse("multipart/form-data"), locationi);
            RequestBody catrb = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(cati));
            RequestBody subcatrb = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(subcati));

            Call<NewBookingRes> newBookingResCall = retrofitAPI.newBooking(ProducBody, SelfBody, catrb, subcatrb, namerb, phonerb, addreesrb, locationrb, uid);
            newBookingResCall.enqueue(new Callback<NewBookingRes>() {
                @Override
                public void onResponse(Call<NewBookingRes> call, Response<NewBookingRes> response) {
                    if (response.body().getStatus()) {
                        Toast.makeText(getApplicationContext(), "uploaded", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "not Added", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<NewBookingRes> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
    }
});
    }

    private String getLocati() {
        if (ActivityCompat.checkSelfPermission(
                Newbookingactvity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                Newbookingactvity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            return "";
        }
        else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                String address=getCompleteAddressString(lat,longi);
                return address;

            } else {
                return "Unable to find location.";

            }
        }
    }

    private String getCompleteAddressString(double lat, double longi) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, longi, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current address ", strReturnedAddress.toString());
            } else {
                Log.w("My Current  address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current  address", "Canont get Address!");
        }
        return strAdd;
    }

    private void OnGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void pickproductimage() {
        String filename="productphoto";
        File storagedirectory=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File shelfimage=File.createTempFile(filename,".jpg",storagedirectory);
            selectedImageUri1=shelfimage;
            Uri imageuri= FileProvider.getUriForFile(Newbookingactvity.this,"com.monstertechno.loginsignupui",
                    shelfimage   );
            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
            startActivityForResult(intent,SELECT_PICTURE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST_CODE:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickshelfimage();
                }
                else {
                    Toast.makeText(this, "permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
            case CAMERA_REQUEST_CODE1:{
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pickproductimage();
                }
                else {
                    Toast.makeText(this, "permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void pickshelfimage() {
        String filename="shelfphoto";
        File storagedirectory=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File shelfimage=File.createTempFile(filename,".jpg",storagedirectory);
            selectedImageUri2=shelfimage;
            Uri imageuri= FileProvider.getUriForFile(Newbookingactvity.this,"com.monstertechno.loginsignupui",
                    shelfimage   );
            Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
            startActivityForResult(intent,second_picture);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getcategory(String userid) {
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
           getCategory getCategory=new getCategory(userid);
           Call<getCategory>categoryCall=retrofitAPI.getcategory(getCategory);
           categoryCall.enqueue(new Callback<com.monstertechno.loginsignupui.modal.getCategory>() {
               @Override
               public void onResponse(Call<com.monstertechno.loginsignupui.modal.getCategory> call, Response<com.monstertechno.loginsignupui.modal.getCategory> response) {
                   if(response.body().isStatus()){
                       categoryArrayList=response.body().getResponseDataCategory().getCategoryArrayList();
                       categoryspinnerAdapter =new categoryspinnerAdapter(getApplicationContext(),categoryArrayList);
                       category.setAdapter(categoryspinnerAdapter);
                   }
               }

               @Override
               public void onFailure(Call<com.monstertechno.loginsignupui.modal.getCategory> call, Throwable t) {

               }
           });


    }

    private void getSubcategory(Integer categeoryidn) {



        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        subcategoryData subcategoryData=new subcategoryData(categeoryidn);
       Call<subcategoryData> subcategoryDataCall=retrofitAPI.getsubcategory(subcategoryData);
        subcategoryDataCall.enqueue(new Callback<com.monstertechno.loginsignupui.modal.subcategoryData>() {
            @Override
            public void onResponse(Call<com.monstertechno.loginsignupui.modal.subcategoryData> call, Response<com.monstertechno.loginsignupui.modal.subcategoryData> response) {
                if(response.body().getStatus()){
                    subcategorylistArrayList=response.body().getResponseDataSubCategory().getSubcategorylists();
                    subcategorySpinnerAdapter=new subcategorySpinnerAdapter(getApplicationContext(),subcategorylistArrayList);
                    subcategory.setAdapter(subcategorySpinnerAdapter);
                }
            }

            @Override
            public void onFailure(Call<com.monstertechno.loginsignupui.modal.subcategoryData> call, Throwable t) {

            }
        });
/**
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        List<String> subcategoryname=new ArrayList<>();

        subcategoryData subcategoryData=new subcategoryData(categeoryidn);
        Call<subcategoryData> subcategoryDataCall=retrofitAPI.getsubcategory(subcategoryData);
        subcategoryDataCall.enqueue(new Callback<com.monstertechno.loginsignupui.modal.subcategoryData>() {
            @Override
            public void onResponse(Call<com.monstertechno.loginsignupui.modal.subcategoryData> call, Response<com.monstertechno.loginsignupui.modal.subcategoryData> response) {
                List<subcategorylist> subcategorylists;
                if(response.body().getStatus()){
                    subcategorylists=response.body().getResponseDataSubCategory().getSubcategorylists();
                    for (int i = 0; i < subcategorylists.size(); i++) {
                        subcategorylist subcategorylist=subcategorylists.get(i);
String id=subcategorylist.getSubcategory_title()+" id:- "+subcategorylist.getId();
                        subcategoryname.add(id);

                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,subcategoryname);
                    adapter.setDropDownViewResource( android.R.layout
                            .simple_spinner_dropdown_item);
                    subcategory.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<com.monstertechno.loginsignupui.modal.subcategoryData> call, Throwable t) {

            }
        });**/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED) {
            if (resultCode == RESULT_OK) {
                if(requestCode==SELECT_PICTURE){

                    Picasso.get().load(selectedImageUri1).into(Productphoto);
                }
                else if(requestCode==second_picture){

                    Picasso.get().load(selectedImageUri2).into(shelfphoto);
                }
            }
        }
    }





}