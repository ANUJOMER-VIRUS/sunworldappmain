package com.monstertechno.loginsignupui.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.modal.Allbooking;
import com.monstertechno.loginsignupui.modal.name;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {
  List<Allbooking> nameList;

    public BookingAdapter(List<Allbooking> nameList) {
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.name_item,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.nametv.setText(nameList.get(position).getCustomerName());
        holder.phone.setText(nameList.get(position).getCustomerPhone());
        holder.address.setText(nameList.get(position).getCustomerAddress());
        holder.booking.setText(nameList.get(position).getBookingDate());
        holder.orderid.setText(nameList.get(position).getOrderId());
        holder.status.setText(nameList.get(position).getStatus());
if(nameList.get(holder.getAdapterPosition()).getStatus().equals("Rejected")){
    holder.status.setTextColor(Color.RED);
    holder.coins.setVisibility(View.GONE);
}
else if(nameList.get(holder.getAdapterPosition()).getStatus().equals("In Progress")){
    holder.status.setTextColor(Color.BLUE);
    holder.coins.setVisibility(View.GONE);
}else if(nameList.get(holder.getAdapterPosition()).getStatus().equals("Approved ")){
    holder.status.setTextColor(Color.parseColor("#1bde0d"));
    holder.coins.setVisibility(View.VISIBLE);
    holder.coinget.setText("500");
}
else {
    holder.status.setTextColor(Color.BLACK);
    holder.coins.setVisibility(View.GONE);
}
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView nametv,phone,address,booking,orderid,coinget,status;
      LinearLayout coins;
      CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nametv=itemView.findViewById(R.id.bookingcust);
            phone=itemView.findViewById(R.id.bookingphone);
            address=itemView.findViewById(R.id.bookingaddress);
            booking=itemView.findViewById(R.id.bookingdate);
            orderid=itemView.findViewById(R.id.bookingorderId);
            status=itemView.findViewById(R.id.bookingorderstatus);
            coins=itemView.findViewById(R.id.coinsbook);
            coinget=itemView.findViewById(R.id.coinsboking);
cardView=itemView.findViewById(R.id.bookingcv);









        }

    }
}
