package com.moutamid.e_learningapp.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.e_learningapp.Adapter.Adapter_Message;
import com.moutamid.e_learningapp.Models.Model_Message;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class Chat_Activity extends AppCompatActivity {

    ImageView close;

    // material for offline recyclers
    private String[] message_chat = {"Hi1", "Hi2", "Hi3", "Hi4", "Hi5", "Hi6",};
    private String[] time_chat = {"Just Now ", "9 hours ago " , "9 hours ago " , "Just Now ", "9 hours ago ", "Just Now",};
    private int[] image_chat = {R.drawable.boy, R.drawable.boy2 , R.drawable.boy ,R.drawable.boy2, R.drawable.boy, R.drawable.boy2, };

    private RecyclerView chat_rcycler;
    private ArrayList<Model_Message> messageArrayList;
    private Adapter_Message adapter_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        close = findViewById(R.id.close_chat);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chat_Activity.this , MainActivity.class);
                startActivity(intent);
                Animatoo.animateSlideDown(Chat_Activity.this);
                finish();
            }
        });

        chat_rcycler = findViewById(R.id.recyclerView_chat);
        loadchat();
    }

    // data of recycler view call in loop
    private void loadchat() {
        messageArrayList = new ArrayList<>();

        for (int i = 0; i < message_chat.length; i++) {
            Model_Message modelAndroid = new Model_Message(
                    message_chat[i],
                    image_chat[i],
                    time_chat[i]
            );
            messageArrayList.add(modelAndroid);
        }
        adapter_message = new Adapter_Message(this, messageArrayList);
        chat_rcycler.setAdapter(adapter_message);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(Chat_Activity.this , MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideDown(Chat_Activity.this);
        finish();
    }
}