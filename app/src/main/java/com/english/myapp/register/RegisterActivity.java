package com.english.myapp.register;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.english.myapp.MainActivity;
import com.english.myapp.R;
import com.english.myapp.SubjectTestUnit;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail, userPassword, userPassword2;
    private CircularProgressButton btnReg;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        setContentView(R.layout.activity_register);


        userEmail = findViewById(R.id.editTextEmail);
        userPassword = findViewById(R.id.password);
        userPassword2 = findViewById(R.id.editTextPassword);
        btnReg = findViewById(R.id.cirRegisterButton);


        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Register();
            }
        });
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    private void Register() {
        String email = userEmail.getText().toString();
        String password1 = userPassword.getText().toString();
        String password2 = userPassword2.getText().toString();
        if (TextUtils.isEmpty(email)) {
            userEmail.setError("Enter your email");
            return;
        } else if (TextUtils.isEmpty(password1)) {
            userPassword.setError("Enter your password");
            return;
        } else if (TextUtils.isEmpty(password2)) {
            userPassword2.setError("Confirm your password");
            return;
        } else if (!password1.equals(password2)) {
            userPassword2.setError("Different password");
            return;
        } else if (password1.length() < 6) {
            userPassword.setError("Length should be > 6");
            return;
        } else if (!isVallidEmail(email)) {
            userEmail.setError("invalid email");
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Sign up fail!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private Boolean isVallidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }
    }
}
