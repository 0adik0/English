package com.english.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelwvwls);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
        // Кнопка для перехода на 1 уровень
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(GameLevels.this, Level1.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });
    // --

    // Кнопка для перехода на 2 уровень
    TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Intent intent = new Intent(GameLevels.this, ActivityLevel2.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
            }
        }
    });
}
    // --

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }
}