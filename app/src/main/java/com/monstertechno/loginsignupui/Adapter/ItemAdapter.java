package com.monstertechno.loginsignupui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.modal.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<Model> itemList1;

    public ItemAdapter(List<Model> itemList) {

        this.itemList1=itemList;
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

        viewHolder.itemImage.setImageResource(itemList1.get(i).getImage());
        viewHolder.itemtext.setText(itemList1.get(i).getName());

    }

    @Override
    public int getItemCount() {
        return itemList1.size();
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