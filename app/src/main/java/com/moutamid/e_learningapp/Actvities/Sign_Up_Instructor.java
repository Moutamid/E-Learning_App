package com.moutamid.e_learningapp.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.e_learningapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Sign_Up_Instructor extends AppCompatActivity {

    Button submit_login_btn;
    CardView select_image;
    CircleImageView profile_image;

    Uri uri;
    private static final int IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_instructor);

        select_image = findViewById(R.id.select_image);
        profile_image = findViewById(R.id.profile_image);

        select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        submit_login_btn = findViewById(R.id.Register_btn);
        submit_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Sign_Up_Instructor.this, MainActivity.class);
                startActivity(homeIntent);
                Animatoo.animateFade(Sign_Up_Instructor.this);
                finish();
            }
        });
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            uri = data.getData();
            profile_image.setImageURI(uri);
        }
        else {
            Toast.makeText(Sign_Up_Instructor.this, "No Image is Selected...", Toast.LENGTH_SHORT).show();
        }
    }
}