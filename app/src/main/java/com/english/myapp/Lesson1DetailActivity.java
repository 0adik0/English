package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Lesson1DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson1_detail);

        //declaring the scrollview and floating action buttons
        final NestedScrollView scroll = findViewById(R.id.scroll);
        FloatingActionButton fabTest = findViewById(R.id.fabTest);
        FloatingActionButton fabNext = findViewById(R.id.fabNext);

        //defining the necessary title to scroll to via button press
        final TextView title1 = findViewById(R.id.firsttitle);
        //defining linking buttons
        Button linkbtn1 = findViewById(R.id.linkbut1);
        linkbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int top = title1.getTop();
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.scrollTo(0, top);
                    }
                });
            }
        });
    }
}