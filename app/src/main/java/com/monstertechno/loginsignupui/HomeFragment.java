package com.monstertechno.loginsignupui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    List<Model> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //  initData();

        recyclerView.setAdapter(new ItemAdapter(initData()));


        return view;




    }

    private List<Model> initData() {

        itemList=new ArrayList<>();
        itemList.add(new Model(R.drawable.logosunworld,"Sunworld Tank","Good","1","Toady"));
        itemList.add(new Model(R.drawable.logosunworld,"Sunworld Tank","Good","2","Toady"));
        itemList.add(new Model(R.drawable.logosunworld,"Sunworld Tank","Good","3","Toady"));
        itemList.add(new Model(R.drawable.logosunworld,"Sunworld Tank","Good","3","Toady"));
        itemList.add(new Model(R.drawable.logosunworld,"Sunworld Tank","Good","4","Ty"));
        itemList.add(new Model(R.drawable.logosunworld,"Sunworld Tank","Good","5","Toady"));


        return itemList;
    }
}