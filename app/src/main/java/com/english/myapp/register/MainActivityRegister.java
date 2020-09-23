package com.english.myapp.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.english.myapp.GameLevels;
import com.english.myapp.Level1;
import com.english.myapp.MainActivity;
import com.english.myapp.R;
import com.english.myapp.StudyMenu;

public class MainActivityRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);

        Button btnlogUp = findViewById(R.id.btn_LogUp);
        btnlogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });

        Button btnlogIn = findViewById(R.id.btn_LogIn);
        btnlogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            }
        });
    }
}