package com.monstertechno.loginsignupui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.activity.redeemProductPage;
import com.monstertechno.loginsignupui.modal.Redeems;
import com.squareup.picasso.Picasso;

import java.util.List;

public class reddemAdapter extends RecyclerView.Adapter<reddemAdapter.ViewHolder> {
List<Redeems> redeemsList;
Context context;
    public reddemAdapter(List<Redeems> redeemsList,Context context) {
        this.redeemsList = redeemsList;
        this.context=context;
    }

    @NonNull
    @Override
    public reddemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.redeem_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull reddemAdapter.ViewHolder holder, int p) {
        int position= holder.getAdapterPosition();
        Picasso.get().load(redeemsList.get(position).getImage()).into(holder.imageView);
        holder.productname.setText(redeemsList.get(position).getName());
        holder.coin.setText(redeemsList.get(position).getCoin());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, redeemProductPage.class);
                intent.putExtra("product_id",redeemsList.get(position).getId());
                intent.putExtra("product_name",redeemsList.get(position).getName());
intent.putExtra("coin",redeemsList.get(position).getCoin());
                intent.putExtra("des",redeemsList.get(position).getDes());

                intent.putExtra("product_url",redeemsList.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return redeemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView productname,coin;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout=itemView.findViewById(R.id.redeemRL);
            imageView=itemView.findViewById(R.id.redeemimage);
            productname=itemView.findViewById(R.id.redeemname);
            coin=itemView.findViewById(R.id.redeemcoin);
        }
    }
}
