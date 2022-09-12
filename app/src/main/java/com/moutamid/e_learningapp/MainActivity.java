package com.moutamid.e_learningapp;


import java.io.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import com.moutamid.e_learningapp.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item1);*/

    }
    /*DiscoverFragment discoverFragment = new DiscoverFragment();
    SearchFragment searchFragment = new SearchFragment();
    MyCourseFragment myCourseFragment = new MyCourseFragment();
    ChatFragment chatFragment = new ChatFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, discoverFragment).commit();
                return true;

            case R.id.item2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
                return true;

            case R.id.item3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, myCourseFragment).commit();
                return true;

            case R.id.item4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();
                return true;

            case R.id.item5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                return true;
        }
        return false;
    }*/
}