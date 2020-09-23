package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    Dialog dialog;
    EditText profileName;
    TextView welcomeText;
    Button goToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        welcomeText = findViewById(R.id.welcome_text);

        dialog = new Dialog(this, R.style.FullScreenDialogStyle);
        View view = getLayoutInflater().inflate(R.layout.enter_name_dialog, null);
        dialog.setContentView(view);

        Button btnAccept = (Button)dialog.findViewById(R.id.btnAccept);
        profileName = (EditText)dialog.findViewById(R.id.profile_name);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(profileName.getText());
                String address = "Приветствую, " + name;
                welcomeText.setText(address);
                dialog.dismiss();
            }
        });
        dialog.show();

        goToProfile = findViewById(R.id.btn_go_profile);
        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), StudyMenu.class));
            }
        });
    }
}