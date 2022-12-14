package com.moutamid.e_learningapp.Actvities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.snackbar.Snackbar;
import com.moutamid.e_learningapp.R;

public class Splash extends AppCompatActivity implements ConnectionReceiver.ReceiverListener{

    private static int SPLASH_TIME_OUT = 3000;
    TextView connection_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        connection_text = findViewById(R.id.connection_text);
        checkConnection();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkConnection();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnection();
    }

    private void checkConnection() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");
        registerReceiver(new ConnectionReceiver(), intentFilter);
        ConnectionReceiver.Listener = this;
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        showSnackBar(isConnected);
    }

    private void showSnackBar(boolean isConnected) {
        // check condition
        if (isConnected) {
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
        } else {
            connection_text.setText("No Internet Access");
        }
    }

    // here we check the intenet connection
    @Override
    public void onNetworkChange(boolean isConnected) {
        // display snack bar
        showSnackBar(isConnected);
    }
}