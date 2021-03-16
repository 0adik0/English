package com.english.myapp.lessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.english.myapp.AboutActivity;
import com.english.myapp.R;

public class Lesson1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);
        WebView lessonView = findViewById(R.id.lessonWeb);
        lessonView.loadUrl("file:///android_asset/Lesson1/Lesson1.html");
        lessonView.setBackgroundColor(Color.parseColor("#E0E0E0"));
        WebSettings ws = lessonView.getSettings();
        ws.setJavaScriptEnabled(true);
        lessonView.addJavascriptInterface(new WebLessonInterface(this), "Android");
    }

    public class WebLessonInterface {
        Context mContext;

        WebLessonInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void goTest() {
            startActivity(new Intent(getBaseContext(), AboutActivity.class));
        }
    }
}