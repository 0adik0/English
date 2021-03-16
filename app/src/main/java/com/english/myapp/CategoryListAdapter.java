package com.english.myapp;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class CategoryListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    String[] companyList;
    Bitmap[] logoList;

    public CategoryListAdapter(String[] companyList, Bitmap[] logoList) {
        this.companyList = companyList;
        this.logoList = logoList;
    }


    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_category_item_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.logo.setImageBitmap(logoList[position]);
        holder.logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
        holder.name.setText(companyList[position]);
    }

    @Override
    public int getItemCount() {
        return companyList.length;
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    public ImageView logo;
    public TextView name;

    public MyViewHolder(View itemView) {
        super(itemView);
        logo = (ImageView)itemView.findViewById(R.id.category_item_icon);
        name = (TextView)itemView.findViewById(R.id.category_item_title);
    }
}


