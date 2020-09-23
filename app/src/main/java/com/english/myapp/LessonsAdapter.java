package com.english.myapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.ItemViewHolder> {

    private static ClickListener clickListener;

    List<Lesson> lessons;

    LessonsAdapter(List<Lesson> lessons){
        this.lessons = lessons;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lesson_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.title.setText(lessons.get(position).title);
        holder.icon.setImageResource(lessons.get(position).iconId);
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private ImageView icon;

        public ItemViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView)itemView.findViewById(R.id.lessonItem);
            icon = (ImageView)itemView.findViewById(R.id.lessonImage);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        LessonsAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
