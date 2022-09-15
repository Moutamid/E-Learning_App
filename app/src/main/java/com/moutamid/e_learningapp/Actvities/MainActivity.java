package com.moutamid.e_learningapp.Actvities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moutamid.e_learningapp.Fragments.AccountFragment;
import com.moutamid.e_learningapp.Fragments.ChatFragment;
import com.moutamid.e_learningapp.Fragments.DiscoverFragment;
import com.moutamid.e_learningapp.Fragments.MyCourseFragment;
import com.moutamid.e_learningapp.Fragments.SearchFragment;
import com.moutamid.e_learningapp.R;

import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.item1);

    }

    DiscoverFragment discoverFragment = new DiscoverFragment();
    SearchFragment searchFragment = new SearchFragment();
    MyCourseFragment myCourseFragment = new MyCourseFragment();
    ChatFragment chatFragment = new ChatFragment();
    AccountFragment accountFragment = new AccountFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment , discoverFragment).commit();
                return true;

            case R.id.item2:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, searchFragment).commit();
                return true;

            case R.id.item3:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, myCourseFragment).commit();
                return true;

            case R.id.item4:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, chatFragment).commit();
                return true;

            case R.id.item5:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, accountFragment).commit();
                return true;
        }
        return false;
    }

}