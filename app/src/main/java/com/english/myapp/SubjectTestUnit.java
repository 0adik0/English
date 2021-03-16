package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubjectTestUnit extends AppCompatActivity {

    RecyclerView subjectTestUnitRecycler;
    List<Test> tests;
    TestsAdapter testsAdapter;
    public boolean[] completedTasks;

    public final String TAG = "myLogs";
    final int FIRST_REQUEST_CODE = 1;
    final int SECOND_REQUEST_CODE = 2;
    final int THIRD_REQUEST_CODE = 3;
    final int FOURTH_REQUEST_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //restoring data in case of changing display's orientation
        if (savedInstanceState!= null){
            completedTasks = savedInstanceState.getBooleanArray("completed");
        }
        else {
            completedTasks = new boolean[]{false, false, false, false};
        }
        setContentView(R.layout.subject_test_unit);
        //setting a back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initializing an ArrayList for tests elements
        tests = new ArrayList<>(Arrays.asList(Test.tests));
        //initializing recyclerView and setting an adapter to it
        subjectTestUnitRecycler = findViewById(R.id.subjectTestUnitRecycler);
        subjectTestUnitRecycler.setLayoutManager(new LinearLayoutManager(this));
        testsAdapter = new TestsAdapter(tests, completedTasks);
        subjectTestUnitRecycler.setAdapter(testsAdapter);
        testsAdapter.setListener(new TestsAdapter.Listener() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        startActivityForResult(new Intent(SubjectTestUnit.this, SubjectFirstTest.class), FIRST_REQUEST_CODE);
                        break;
                    case 1:
                        startActivityForResult(new Intent(SubjectTestUnit.this, SubjectSecondTest.class), SECOND_REQUEST_CODE);
                        break;
                    case 2:
                        startActivityForResult(new Intent(SubjectTestUnit.this, SubjectThirdTest.class), THIRD_REQUEST_CODE);
                        break;
                    case 3:
                        startActivityForResult(new Intent(SubjectTestUnit.this, SubjectFourthTest.class), FOURTH_REQUEST_CODE);
                        break;
                }
            }
        });
    }

    //getting the result of completing tasks
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            switch (requestCode){
                case FIRST_REQUEST_CODE:
                    completedTasks[0] = data.getBooleanExtra("firstResult", false);
                    testsAdapter.notifyDataSetChanged();
                    break;
                case SECOND_REQUEST_CODE:
                    completedTasks[1] = data.getBooleanExtra("secondResult", false);
                    testsAdapter.notifyDataSetChanged();
                    break;
                case THIRD_REQUEST_CODE:
                    completedTasks[2] = data.getBooleanExtra("thirdResult", false);
                    testsAdapter.notifyDataSetChanged();
                    break;
                case FOURTH_REQUEST_CODE:
                    completedTasks[3] = data.getBooleanExtra("fourthResult", false);
                    testsAdapter.notifyDataSetChanged();
                    break;
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putBooleanArray("completed", completedTasks);
        Log.d(TAG, "successfully saved, completed 1 = " + completedTasks[0] + " completed 2 = " + completedTasks[1]);
        super.onSaveInstanceState(savedInstanceState);
    }
}