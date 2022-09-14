package com.moutamid.e_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private String[] chat_title = {"Tutor of Wordpress Web Development", "Tutor of Android App Development", "Tutor of Graphics Designing Course", "Tutor of SEO Course",};
    private String[] chat_tutor = {"Mr. Osama", "Mr. Ali", "Mr. Moutamid","Mr. Example",};
    private int[] images1_detail = {R.drawable.boy, R.drawable.boy2, R.drawable.boy, R.drawable.boy2};

    private RecyclerView detail_recycler;
    private ArrayList<Model_Chat> modelChatArrayList;
    private Adapter_Chat adapter_chat;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
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
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_chat);
        load_detail();
        return view;
    }

    private void load_detail() {
        modelChatArrayList = new ArrayList<>();

        for (int i = 0; i < chat_title.length; i++) {
            Model_Chat modelAndroid = new Model_Chat(
                    chat_tutor[i],
                    chat_title[i],
                    images1_detail[i]
            );
            modelChatArrayList.add(modelAndroid);
        }
        adapter_chat = new Adapter_Chat(getContext(), modelChatArrayList);
        detail_recycler.setAdapter(adapter_chat);
    }
}