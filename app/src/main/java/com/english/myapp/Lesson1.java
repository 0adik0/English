package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;

public class Lesson1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);
        WebView lessonView = findViewById(R.id.lessonWeb);
        lessonView.loadUrl("file:///android_asset/Lesson1/Lesson1.html");
        lessonView.setBackgroundColor(Color.parseColor("#E0E0E0"));
    }
}