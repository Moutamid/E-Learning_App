package com.moutamid.e_learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sign_Up_Activity extends AppCompatActivity {

    TextView goto_signup;
    Button submit_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        goto_signup = findViewById(R.id.goto_signin);
        goto_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Sign_Up_Activity.this, Login_Activity.class);
                startActivity(homeIntent);
                finish();
            }
        });

        submit_login_btn = findViewById(R.id.Register_btn);
        submit_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Sign_Up_Activity.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}