package com.english.myapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.english.myapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Profile extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

    }
}