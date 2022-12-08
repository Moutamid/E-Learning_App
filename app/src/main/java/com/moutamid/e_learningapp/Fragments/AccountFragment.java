package com.moutamid.e_learningapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.e_learningapp.Actvities.Login_Activity;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.UserModel;
import com.moutamid.e_learningapp.R;
import com.moutamid.e_learningapp.Actvities.Sign_Up_Activity;
import com.moutamid.e_learningapp.Actvities.Sign_Up_Instructor;
import com.moutamid.e_learningapp.Actvities.Upload_Content;

public class AccountFragment extends Fragment {

    Button singIn , signUp , instructor , content_btn, logout;

    public AccountFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        content_btn = view.findViewById(R.id.content_btn);
        instructor = view.findViewById(R.id.instructor);
        singIn = view.findViewById(R.id.singIn);
        signUp = view.findViewById(R.id.signUp);
        logout = view.findViewById(R.id.logout);

        if (Constants.auth().getCurrentUser() == null){
            content_btn.setVisibility(View.GONE);
            logout.setVisibility(View.GONE);
        } else {
            Constants.databaseReference().child("users")
                    .child(Constants.auth().getCurrentUser().getUid())
                    .get().addOnCompleteListener(task -> {
                       if (task.isSuccessful()){
                           UserModel model = task.getResult().getValue(UserModel.class);
                           if (model.isInstructor()){
                               content_btn.setVisibility(View.VISIBLE);
                               instructor.setVisibility(View.GONE);
                               signUp.setVisibility(View.GONE);
                               singIn.setVisibility(View.GONE);
                               logout.setVisibility(View.VISIBLE);
                           } else {
                               content_btn.setVisibility(View.GONE);
                               instructor.setVisibility(View.GONE);
                               signUp.setVisibility(View.GONE);
                               singIn.setVisibility(View.GONE);
                               logout.setVisibility(View.VISIBLE);
                           }
                       }
                    });
        }
        logout.setOnClickListener(v -> {
            Constants.auth().signOut();
            getActivity().recreate();
        });
        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , Login_Activity.class);
                Animatoo.animateFade(getContext());
                startActivity(intent);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , Sign_Up_Activity.class);
                Animatoo.animateFade(getContext());
                startActivity(intent);
            }
        });
        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , Sign_Up_Instructor.class);
                Animatoo.animateFade(getContext());
                startActivity(intent);
            }
        });
        content_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , Upload_Content.class);
                Animatoo.animateFade(getContext());
                startActivity(intent);
            }
        });

        return view;
    }
}