package com.moutamid.e_learningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moutamid.e_learningapp.Adapter.Adapter_Courses;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.Model_Courses;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {

    private String[] course_title = {"Wordpress Web Development", "Android App Development", "Graphics Designing Course",};
    private String[] course_tutor = {"By Mr. Osama", "By Mr. Ali", "By Mr. Moutamid",};
    private String[] course_member = {"603", "1k+", "390",};
    private String[] course_efficient = {"100%", "90%", "20%",};
    private String[] course_price = {"FREE", "1999.00 PKR", "FREE",};
    private String[] course_status = {"New Course", "Best Seller", "New Course",};
    private String[] course_time = {"2h 39mins", "7h 00mins", "2h 05mins",};
    private String[] course_des = {
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",};

    private int[] images1_detail = {R.drawable.img1, R.drawable.img2, R.drawable.img3,};

    private RecyclerView detail_recycler;
    private ArrayList<Model_Courses> modelCoursesArrayList;
    private Adapter_Courses adapter_courses;
    Model_Courses model_courses;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public DiscoverFragment() {
    }
    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_courses);

        load_detail();

        Constants.databaseReference().child("courses");

        return view;
    }

    private void load_detail() {
        modelCoursesArrayList = new ArrayList<>();

        for (int i = 0; i < course_title.length; i++) {
            Model_Courses modelAndroid = new Model_Courses(
                    course_title[i],
                    course_tutor[i],
                    course_member[i],
                    course_efficient[i],
                    course_price[i],
                    course_status[i],
                    course_time[i],
                    course_des[i],
                    images1_detail[i]
            );
            modelCoursesArrayList.add(modelAndroid);
        }
        adapter_courses = new Adapter_Courses(getContext(), modelCoursesArrayList);
        detail_recycler.setAdapter(adapter_courses);
    }
}