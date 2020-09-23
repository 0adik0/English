package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyMenu extends AppCompatActivity {

    RecyclerView unitsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_menu);
        unitsRecycler = (RecyclerView) findViewById(R.id.recycleView);
        unitsRecycler.setLayoutManager(new LinearLayoutManager(this));
        List<Unit> units = new ArrayList<>(Arrays.asList(Unit.units));
        UnitsAdapter unitsAdapter = new UnitsAdapter(units);
        unitsRecycler.setAdapter(unitsAdapter);

        unitsAdapter.setListener(new UnitsAdapter.Listener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getBaseContext(), LessonsMenu.class));
                        break;
                }
            }
        });
    }
}