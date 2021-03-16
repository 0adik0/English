package com.english.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

public final class ImageSliderAdapter extends PagerAdapter {

    private final Context mContext;
    private final int[] itemImage;
    private final String[] itemTitle;

    public ImageSliderAdapter(Context context, int[] itemBgArray, String[] itemTitleArray){
        mContext = context;
        itemImage = itemBgArray;
        itemTitle = itemTitleArray;
    }

    public void destroyItem(ViewGroup viewGroup, int i, @NotNull Object obj) {
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(@NotNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.course_item_card, viewGroup, false);
        ImageView itemBg = itemView.findViewById(R.id.unitBg);
        itemBg.setImageResource(itemImage[position]);
        TextView itemName = itemView.findViewById(R.id.unitTitle);
        itemName.setText(itemTitle[position]);
        viewGroup.addView(itemView);
        return itemView;
    }

    public int getCount() {
        return this.itemImage.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
