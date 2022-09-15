package com.moutamid.e_learningapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moutamid.e_learningapp.Adapter.Adapter_Community_Chat;
import com.moutamid.e_learningapp.Models.Modal_Community_Chat;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class Community_Fragment extends Fragment {

    private String[] message_chat = {"Hi1", "Hi2", "Hi3", "Hi4", "Hi5", "Hi6",};
    private String[] time_chat = {"Just Now ", "9 hours ago " , "9 hours ago " , "Just Now ", "9 hours ago ", "Just Now",};
    private int[] image_chat = {R.drawable.boy, R.drawable.boy2 , R.drawable.boy ,R.drawable.boy2, R.drawable.boy, R.drawable.boy2, };

    private RecyclerView detail_recycler;
    private ArrayList<Modal_Community_Chat> modalCommunityChatArrayList;
    private Adapter_Community_Chat adapterCommunityChat;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public Community_Fragment() {
    }

    public static Community_Fragment newInstance(String param1, String param2) {
        Community_Fragment fragment = new Community_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_community_, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_chat_community);
        load_detail();
        return view;    }

    private void load_detail() {
        modalCommunityChatArrayList = new ArrayList<>();

        for (int i = 0; i < message_chat.length; i++) {
            Modal_Community_Chat modelAndroid = new Modal_Community_Chat(
                    message_chat[i],
                    image_chat[i],
                    time_chat[i]
            );
            modalCommunityChatArrayList.add(modelAndroid);
        }
        adapterCommunityChat = new Adapter_Community_Chat(getContext() , modalCommunityChatArrayList);
        detail_recycler.setAdapter(adapterCommunityChat);
    }
}