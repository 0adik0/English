package com.english.myapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TestsAdapter extends RecyclerView.Adapter<TestsAdapter.UnitViewHolder> {

    private Listener listener;
    List<Test> tests;

    private final int TYPE_COMPLETED = 0;
    private final int TYPE_NOT_COMPLETED = 1;
    private final boolean[] completedTasks;

    TestsAdapter(List<Test> tests, boolean[] completedTasks){
        this.tests = tests;
        this.completedTasks = completedTasks;
    }

    public interface Listener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UnitViewHolder cv;
        CardView view;
        if(viewType == 0) {
            view = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.test_item_card_completed, parent, false);
        }
        else {
            view = (CardView) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.test_item_card, parent, false);
        }
        cv = new UnitViewHolder(view);
        return cv;
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView)cardView.findViewById(R.id.testImage);
        TextView textView = (TextView)cardView.findViewById(R.id.testText);
        textView.setText(tests.get(position).title);
        //getting the viewType in a current position

        int type = getItemViewType(position);
        switch (type){
            case TYPE_NOT_COMPLETED:
                imageView.setImageResource(R.drawable.test_icon);
                break;
            case TYPE_COMPLETED:
                imageView.setImageResource(R.drawable.ok_icon);
                cardView.setCardBackgroundColor(Color.GREEN);
                break;
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    public int getItemViewType(int position){
        switch (position){
            case 0:
                if (completedTasks[0]){
                    return TYPE_COMPLETED;
                }
                else{
                    return TYPE_NOT_COMPLETED;
                }
            case 1:
                if (completedTasks[1]){
                    return TYPE_COMPLETED;
                }
                else{
                    return TYPE_NOT_COMPLETED;
                }
            case 2:
                if (completedTasks[2]){
                    return TYPE_COMPLETED;
                }
                else{
                    return TYPE_NOT_COMPLETED;
                }
            case 3:
                if (completedTasks[3]){
                    return TYPE_COMPLETED;
                }
                else{
                    return TYPE_NOT_COMPLETED;
                }
            default:
                return TYPE_NOT_COMPLETED;
        }
    }


    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
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
