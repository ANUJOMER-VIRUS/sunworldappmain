package com.monstertechno.loginsignupui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.activity.Newbookingactvity;
import com.monstertechno.loginsignupui.activity.UserProfileActivity;

public class NewBookFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        startActivity(new Intent(getActivity(), Newbookingactvity.class));
        View view=inflater.inflate(R.layout.fragment_new_book, container, false);
        Button button=view.findViewById(R.id.newbookingbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Newbookingactvity.class));
            }
        });
        return view;

    }
}