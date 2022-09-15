package com.moutamid.e_learningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moutamid.e_learningapp.Adapter.Adapter_Enrolled;
import com.moutamid.e_learningapp.Models.Model_Enrolled;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class MyCourseFragment extends Fragment {

    private String[] course_title = {"Wordpress Web Development", "Android App Development", "Graphics Designing Course", "SEO Course",};
    private String[] course_tutor = {"By Mr. Osama", "By Mr. Ali", "By Mr. Moutamid","By Mr. Example",};
    private int[] images1_detail = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.course_img};

    private RecyclerView detail_recycler;
    private ArrayList<Model_Enrolled> modelEnrolledArrayList;
    private Adapter_Enrolled adapter_enrolled;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public MyCourseFragment() {
    }
    public static MyCourseFragment newInstance(String param1, String param2) {
        MyCourseFragment fragment = new MyCourseFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_enrolled);
        load_detail();
        return view;
    }

    private void load_detail() {
        modelEnrolledArrayList = new ArrayList<>();

        for (int i = 0; i < course_title.length; i++) {
            Model_Enrolled modelAndroid = new Model_Enrolled(
                    course_title[i],
                    course_tutor[i],
                    images1_detail[i]
            );
            modelEnrolledArrayList.add(modelAndroid);
        }
        adapter_enrolled = new Adapter_Enrolled(getContext(), modelEnrolledArrayList);
        detail_recycler.setAdapter(adapter_enrolled);
    }
}