package com.english.myapp.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.english.myapp.R;
import com.english.myapp.StudyMenu;
import com.english.myapp.register.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        updateinfaprofile();

        TextView startText = findViewById(R.id.textStartCourses);
        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), StudyMenu.class));
            }
        });
    }

    public void updateinfaprofile() {
        TextView userName = findViewById(R.id.user_text);
        userName.setText(currentUser.getDisplayName());
        CircleImageView userPhoto = (CircleImageView) findViewById(R.id.profile_img);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(userPhoto);

    }
}