package com.monstertechno.loginsignupui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.Retrofit.RetrofitAPI;
import com.monstertechno.loginsignupui.Retrofit.RetrofitClient;
import com.monstertechno.loginsignupui.activity.PrivacyPolicy;
import com.monstertechno.loginsignupui.activity.SiginActivity;
import com.monstertechno.loginsignupui.activity.Termandcondition;
import com.monstertechno.loginsignupui.activity.UserProfileActivity;
import com.monstertechno.loginsignupui.modal.ResponseDataUser;
import com.monstertechno.loginsignupui.modal.UserProfile;
import com.monstertechno.loginsignupui.modal.UserResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_profile, container, false);
      CardView relativeLayout=v.findViewById(R.id.layout1);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UserProfileActivity.class));
            }
        });
        CardView relativeLayout2=v.findViewById(R.id.layout4);
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PrivacyPolicy.class));
            }
        });
      CardView relativeLayout3=v.findViewById(R.id.layout5);
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Termandcondition.class));
            }
        });


        CircleImageView imageView=v.findViewById(R.id.profile_image);
        TextView Nametv=v.findViewById(R.id.namep);
        TextView citytv=v.findViewById(R.id.city);
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String Userid=sharedPreferences.getString("userid", "not_found");
        UserResponse userResponse=new UserResponse(Userid);
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<UserResponse> call=retrofitAPI.getuserProfile(userResponse);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.body().getStatus()){
                    ResponseDataUser responseDataUser=response.body().getResponseData();
                    List<UserProfile> userProfiles=responseDataUser.getUserProfile();
                    UserProfile userProfile;
                    userProfile=userProfiles.get(0);
                    Nametv.setText(userProfile.getFullName());
                    citytv.setText(userProfile.getCity());
                    Picasso.get().load(userProfile.getImage()).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
CardView relativeLayout1=v.findViewById(R.id.layout6);
relativeLayout1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Logout");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes",(DialogInterface.OnClickListener)(dialog,which)-> {
            editor.putString("islogin","no");
            editor.commit();
            startActivity(new Intent(getActivity(), SiginActivity.class));
           getActivity().finish();

        });
        builder.setNegativeButton("No",(DialogInterface.OnClickListener) (dialog,which)->{
            dialog.cancel();
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
});

        return v;
    }
}