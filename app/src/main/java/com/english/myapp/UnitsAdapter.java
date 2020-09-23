package com.english.myapp;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitViewHolder> {

    private Listener listener;
    List<Unit> units;

    UnitsAdapter(List<Unit> units){
        this.units = units;
    }

    public interface Listener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.study_unit_card, parent, false);
        return new UnitViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, final int position) {
        CardView cardView = holder.cardView;

        ImageView imageView = (ImageView)cardView.findViewById(R.id.unitBg);
        int img = units.get(position).imageId;
        if (img == R.drawable.timetravel){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            imageView.setColorFilter(filter);
            imageView.setImageResource(img);
        }
        else {
            imageView.setImageResource(img);
        }

        TextView textView = (TextView)cardView.findViewById(R.id.unitTitle);
        textView.setText(units.get(position).title);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class UnitViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        public UnitViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

    }

}
