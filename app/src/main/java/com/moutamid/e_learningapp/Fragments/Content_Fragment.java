package com.moutamid.e_learningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moutamid.e_learningapp.Adapter.Adapter_Content;
import com.moutamid.e_learningapp.Models.Model_Content;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class Content_Fragment extends Fragment {

    private String[] course_title = {"What is Development ?", "What is Android App Development ?", "Why Graphics Designing Course is Important ?", "Benefits of SEO Course ?",};
    private int[] images1_detail = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.course_img};

    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelContentArrayList;
    private Adapter_Content adapter_content;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public Content_Fragment() {
    }
    public static Content_Fragment newInstance(String param1, String param2) {
        Content_Fragment fragment = new Content_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_content_, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_content);
        load_detail();
        return view;    }

    private void load_detail() {
        modelContentArrayList = new ArrayList<>();

        for (int i = 0; i < course_title.length; i++) {
            Model_Content modelAndroid = new Model_Content(
                    course_title[i],
                    images1_detail[i]
            );
            modelContentArrayList.add(modelAndroid);
        }
        adapter_content = new Adapter_Content(getContext(), modelContentArrayList);
        detail_recycler.setAdapter(adapter_content);
    }
}