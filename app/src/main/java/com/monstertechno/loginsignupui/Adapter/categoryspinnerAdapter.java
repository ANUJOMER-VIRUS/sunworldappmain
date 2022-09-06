package com.monstertechno.loginsignupui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.modal.category;

import java.util.ArrayList;
import java.util.List;

public class categoryspinnerAdapter extends ArrayAdapter<category> {
 public categoryspinnerAdapter(Context context, ArrayList<category> categoryArrayList){
     super(context,0,categoryArrayList);
 }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
    if(convertView==null){
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.categoryspinner,parent,false);

    }
        TextView textView=convertView.findViewById(R.id.text_view);
        category category=getItem(position);
        if(category!=null){
            textView.setText(category.getCategoryTitle());
        }
        return convertView;
 }
}
