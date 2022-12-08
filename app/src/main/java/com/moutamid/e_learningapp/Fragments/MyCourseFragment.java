package com.moutamid.e_learningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.moutamid.e_learningapp.Adapter.Adapter_Enrolled;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.CourseIDs;
import com.moutamid.e_learningapp.Models.Model_Content;
import com.moutamid.e_learningapp.Models.Model_Enrolled;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class MyCourseFragment extends Fragment {

    private String[] course_title = {"Wordpress Web Development", "Android App Development", "Graphics Designing Course", "SEO Course",};
    private String[] course_tutor = {"By Mr. Osama", "By Mr. Ali", "By Mr. Moutamid","By Mr. Example",};
    private int[] images1_detail = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.course_img};

    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelEnrolledArrayList;
    private Adapter_Enrolled adapter_enrolled;

    ArrayList<CourseIDs> courseIDs = new ArrayList<>();

    public MyCourseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_course, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_enrolled);

        modelEnrolledArrayList = new ArrayList<>();

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .child("enrolled").get()
                .addOnSuccessListener(dataSnapshot -> {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        CourseIDs model = ds.getValue(CourseIDs.class);
                        courseIDs.add(model);
                    }
                    getData();
                }).addOnFailureListener(e -> {

                });

        // load_detail();
        return view;
    }

    private void getData() {
        for (int i = 0; i < courseIDs.size(); i++) {
            Constants.databaseReference().child("course_contents").child(courseIDs.get(i).getSellerID())
                    .child(courseIDs.get(i).getID())
                    .get().addOnSuccessListener(dataSnapshot -> {
                        Model_Content model = dataSnapshot.getValue(Model_Content.class);
                        modelEnrolledArrayList.add(model);
                        adapter_enrolled = new Adapter_Enrolled(getContext(), modelEnrolledArrayList);
                        detail_recycler.setAdapter(adapter_enrolled);
                    })
                    .addOnFailureListener(e -> {
                        e.printStackTrace();
                    });
        }
    }
}