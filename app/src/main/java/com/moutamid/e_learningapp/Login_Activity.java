package com.moutamid.e_learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {

    TextView goto_signup;
    TextView forget_text;
    Button submit_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goto_signup = findViewById(R.id.goto_signup);
        goto_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Login_Activity.this, Sign_Up_Activity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        forget_text = findViewById(R.id.forget_text);
        forget_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Login_Activity.this, Forget_Password.class);
                startActivity(homeIntent);
                finish();
            }
        });

        submit_login_btn = findViewById(R.id.submit_login_btn);
        submit_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Login_Activity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}