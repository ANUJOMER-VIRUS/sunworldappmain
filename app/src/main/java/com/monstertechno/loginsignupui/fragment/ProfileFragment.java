package com.monstertechno.loginsignupui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.activity.UserProfileActivity;


public class ProfileFragment extends Fragment {

    RelativeLayout relativeLayout,relativelayout1,relativelayout2;
    private Object HomeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        relativeLayout = view.findViewById(R.id.layout1);
        relativelayout1 = view.findViewById(R.id.layout2);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}


