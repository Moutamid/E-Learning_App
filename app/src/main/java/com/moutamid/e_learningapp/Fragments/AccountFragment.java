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

    Button singIn , signUp , instructor , content_btn;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AccountFragment() {
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        content_btn = view.findViewById(R.id.content_btn);
        instructor = view.findViewById(R.id.instructor);

        if (Constants.auth().getCurrentUser() == null){
            content_btn.setVisibility(View.GONE);
        } else {
            Constants.databaseReference().child("users")
                    .child(Constants.auth().getCurrentUser().getUid())
                    .get().addOnCompleteListener(task -> {
                       if (task.isSuccessful()){
                           UserModel model = task.getResult().getValue(UserModel.class);
                           if (model.isInstructor()){
                               content_btn.setVisibility(View.VISIBLE);
                               instructor.setVisibility(View.GONE);
                           }
                       }
                    });
        }

        singIn = view.findViewById(R.id.singIn);
        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , Login_Activity.class);
                Animatoo.animateFade(getContext());
                startActivity(intent);
            }
        });

        signUp = view.findViewById(R.id.signUp);
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