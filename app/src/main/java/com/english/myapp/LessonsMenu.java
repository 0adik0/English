package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LessonsMenu extends AppCompatActivity {

    RecyclerView resView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons_menu);
        resView = (RecyclerView) findViewById(R.id.lessonsRecycler);
        resView.setLayoutManager(new LinearLayoutManager(this));
        List<Lesson> lessons = new ArrayList<>(Arrays.asList(Lesson.lessons));
        LessonsAdapter lessonsAdapter = new LessonsAdapter(lessons);
        resView.setAdapter(lessonsAdapter);

        lessonsAdapter.setOnItemClickListener(new LessonsAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getBaseContext(), Lesson1DetailActivity.class));
                        break;
                }
            }
        });
    }
}