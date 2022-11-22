package com.moutamid.e_learningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.moutamid.e_learningapp.Adapter.Adapter_Courses;
import com.moutamid.e_learningapp.Models.Model_Content;
import com.moutamid.e_learningapp.Models.Model_Courses;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelCoursesArrayList;
    private Adapter_Courses adapter_courses;
    EditText search;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
    }
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_courses);
        search = view.findViewById(R.id.search_recycler);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter_courses.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        load_detail();
        return view;    }

    private void load_detail() {
        modelCoursesArrayList = new ArrayList<>();

            Model_Content modelAndroid = new Model_Content(

            );
            modelCoursesArrayList.add(modelAndroid);
        adapter_courses = new Adapter_Courses(getContext(), modelCoursesArrayList);
        detail_recycler.setAdapter(adapter_courses);
    }
}