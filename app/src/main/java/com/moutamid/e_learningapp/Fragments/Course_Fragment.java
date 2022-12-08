package com.moutamid.e_learningapp.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.Model_Content;
import com.moutamid.e_learningapp.R;

public class Course_Fragment extends Fragment {
    String course_ID, sellerID;
    Context context;
    ImageView image;
    TextView title, tutor, member, efficient, status, time, desc;
    Button enroll;

    public Course_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_, container, false);
        context = view.getContext();

        image = view.findViewById(R.id.course_img);
        title = view.findViewById(R.id.course_title);
        tutor = view.findViewById(R.id.course_tutor);
        member = view.findViewById(R.id.course_member);
        efficient = view.findViewById(R.id.course_efficent);
        status = view.findViewById(R.id.course_status);
        time = view.findViewById(R.id.course_time);
        desc = view.findViewById(R.id.course_des);
        enroll = view.findViewById(R.id.btn_enroll);

        course_ID = Stash.getString("ID");
        sellerID = Stash.getString("sellerID");

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .child("enrolled").child(course_ID).get()
                .addOnSuccessListener(dataSnapshot -> {
                    enroll.setText("Enrolled");
                    enroll.setEnabled(false);
                })
                .addOnFailureListener(e -> {
                   e.printStackTrace();
                    enroll.setText("Enroll Now");
                    enroll.setEnabled(true);
                });

        Constants.databaseReference().child("course_contents").child(sellerID).child(course_ID)
                .get().addOnSuccessListener(dataSnapshot -> {
                    Model_Content model = dataSnapshot.getValue(Model_Content.class);
                    Glide.with(view.getContext()).load(model.getImage()).into(image);
                    title.setText(model.getTitle());
                    tutor.setText("By " + model.getTutor());
                    member.setText(""+model.getMember());
                    efficient.setText(model.getEfficient());
                    status.setText(model.getStatus());
                    time.setText(model.getVideo_length());
                    desc.setText(model.getDesc());
                })
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                });

        return view;
    }
}