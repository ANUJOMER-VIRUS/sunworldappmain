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
import com.monstertechno.loginsignupui.modal.subcategorylist;

import java.util.ArrayList;

public class subcategorySpinnerAdapter  extends ArrayAdapter<subcategorylist> {
    public subcategorySpinnerAdapter(Context context, ArrayList<subcategorylist> subcategorylistArrayList) {
        super(context, 0, subcategorylistArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subcategoryspinner, parent, false);

        }
        TextView textView = convertView.findViewById(R.id.subtext_view);
        subcategorylist subcategorylist = getItem(position);
        if (subcategorylist != null) {
            textView.setText(subcategorylist.getSubcategory_title());
        }
        return convertView;
    }
}
