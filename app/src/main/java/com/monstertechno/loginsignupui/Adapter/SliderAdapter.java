package com.monstertechno.loginsignupui.Adapter;

import android.content.Context;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.monstertechno.loginsignupui.R;
import com.monstertechno.loginsignupui.modal.Slider;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
    private final List<Slider> sliders;

    public SliderAdapter(Context context,List<Slider> sliders) {
        this.sliders = sliders;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,null) ;
   return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
final  Slider slider=sliders.get(position);
        Picasso.get().load(slider.getImage()).into(viewHolder.imageSlider);
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    public class SliderAdapterViewHolder extends ViewHolder {
        View itemView;
        ImageView imageSlider;
        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageSlider=itemView.findViewById(R.id.imageslider);
            this.itemView=itemView;
        }
    }
}
