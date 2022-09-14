package com.moutamid.e_learningapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                Intent homeIntent = new Intent(Splash.this, MainActivity.class);
                startActivity(homeIntent);
                Animatoo.animateSlideDown(Splash.this);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}