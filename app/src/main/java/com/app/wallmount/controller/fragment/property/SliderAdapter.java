package com.app.wallmount.controller.fragment.property;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.wallmount.R;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    // List for storing URLs of images.
    List<String> imageUrls;

    // Constructor
    public SliderAdapter(Context context, List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    // Inflate the slider_layout inside onCreateViewHolder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Set data to the item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
        // Load image from URL using Glide.
        Glide.with(viewHolder.itemView)
                .load(imageUrls.get(position))
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    // Return the count of our list.
    @Override
    public int getCount() {
        return imageUrls.size();
    }

    static class SliderAdapterViewHolder extends ViewHolder {
        // Adapter class for initializing views of our slider view.
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageViewMain);
            this.itemView = itemView;
        }
    }
}
