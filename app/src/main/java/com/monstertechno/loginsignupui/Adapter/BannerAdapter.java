package com.monstertechno.loginsignupui.Adapter;

import android.app.appsearch.PackageIdentifier;
import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.modal.Banner;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.BannerAdapterViewHolder> {
private final List<Banner> banners;

    public BannerAdapter(Context context,List<Banner> banners) {
        this.banners = banners;
    }

    @Override
    public BannerAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item,null);
        return new BannerAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BannerAdapterViewHolder viewHolder, int position) {
  final Banner banner=banners.get(position);
        Picasso.get().load(banner.getImage()).into(viewHolder.imageBanner);
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    public class BannerAdapterViewHolder extends ViewHolder {
        View itemView;
        ImageView imageBanner;
        public BannerAdapterViewHolder(View itemView) {
            super(itemView);
            imageBanner=itemView.findViewById(R.id.bannerImage);
            this.itemView=itemView;
        }
    }
}
