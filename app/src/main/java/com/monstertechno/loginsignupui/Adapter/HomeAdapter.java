package com.monstertechno.loginsignupui.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.activity.DetailActivity;
import com.monstertechno.loginsignupui.activity.MainActivity;
import com.monstertechno.loginsignupui.modal.HomeData;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    HomeData[] homeData;
    MainActivity context;

    public HomeAdapter(HomeData[] homeActivityData, MainActivity activity) {
        this.homeData = homeActivityData;
        this.context = activity;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HomeData dataList = homeData[position];
        holder.imageView.setImageResource(dataList.getImage());
        holder.textViewName.setText(dataList.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("image",dataList.getImage());
//                intent.putExtra("name",dataList.getName());
//                context.startActivity(intent);
//

            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("image",dataList.getImage());
                intent.putExtra("name",dataList.getName());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return homeData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName,description;
        Button add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textViewName = itemView.findViewById(R.id.txt);
            description = itemView.findViewById(R.id.description);
            add = itemView.findViewById(R.id.addButton);
        }
    }
}
