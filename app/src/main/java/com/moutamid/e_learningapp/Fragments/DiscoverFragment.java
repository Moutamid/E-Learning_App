package com.moutamid.e_learningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.moutamid.e_learningapp.Adapter.Adapter_Courses;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.Model_Content;
import com.moutamid.e_learningapp.Models.Model_Courses;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {

    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelCoursesArrayList;
    private Adapter_Courses adapter_courses;
    Model_Courses model_courses;


    public DiscoverFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_courses);
        modelCoursesArrayList = new ArrayList<>();
        Constants.databaseReference().child("course_contents")
                .get()
                .addOnSuccessListener(dataSnapshot -> {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        for (DataSnapshot ds2 : ds.getChildren()){
                            Model_Content model = ds2.getValue(Model_Content.class);
                            modelCoursesArrayList.add(model);
                        }
                    }
                    adapter_courses = new Adapter_Courses(view.getContext(), modelCoursesArrayList);
                    detail_recycler.setAdapter(adapter_courses);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        return view;
    }
}