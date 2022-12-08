package com.moutamid.e_learningapp.Actvities;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.moutamid.e_learningapp.Fragments.Community_Fragment;
import com.moutamid.e_learningapp.Fragments.Content_Fragment;
import com.moutamid.e_learningapp.Fragments.Course_Fragment;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;
import java.util.List;

public class Display_Activity extends AppCompatActivity {

    // here this activity display when user click on course
    // here we have 3 tabs content , community and course details

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private Community_Fragment community_fragment;
    private Content_Fragment content_fragment;
    private Course_Fragment course_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);


        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager() , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        content_fragment = new Content_Fragment();
        course_fragment = new Course_Fragment();
        community_fragment = new Community_Fragment();

        // tab layout which is to be displayed in viewpager
        adapter.addFragment(content_fragment, "Content");
        adapter.addFragment(course_fragment, "Course");
        adapter.addFragment(community_fragment, "Community");

        mViewPager.setAdapter(adapter);
    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm, int behaviour) {
            super(fm, behaviour);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }

}