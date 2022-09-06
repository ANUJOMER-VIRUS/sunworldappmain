package com.monstertechno.loginsignupui.Adapter;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.modal.SubCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {



List<SubCategory> categoryArrayList;

    public ItemAdapter(List<SubCategory> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowitem,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {

Picasso.get().load(categoryArrayList.get(i).getImage()).into(viewHolder.itemImage);
        viewHolder.itemtext.setText(categoryArrayList.get(i).getName());
        viewHolder.itemtext1.setText(categoryArrayList.get(i).getId());
        viewHolder.itemtext2.setText(categoryArrayList.get(i).getCoins());
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N) {
            viewHolder.itemtext3.setText(Html.fromHtml(categoryArrayList.get(i).getDescription(), Html.FROM_HTML_MODE_COMPACT));
        }
        else {
            viewHolder.itemtext3.setText(Html.fromHtml(categoryArrayList.get(i).getDescription()));

        }

    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemtext,itemtext1,itemtext2,itemtext3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage=itemView.findViewById(R.id.itemImag);
            itemtext=itemView.findViewById(R.id.productname);
            itemtext1=itemView.findViewById(R.id.itemqty);
            itemtext2=itemView.findViewById(R.id.itemnumber);
            itemtext3=itemView.findViewById(R.id.deleivery);
        }
    }
}