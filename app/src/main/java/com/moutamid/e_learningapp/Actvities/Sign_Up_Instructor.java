package com.moutamid.e_learningapp.Actvities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.UserModel;
import com.moutamid.e_learningapp.R;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Sign_Up_Instructor extends AppCompatActivity {

    Button submit_login_btn;
    CardView select_image;
    CircleImageView profile_image;
    EditText name, email, password, cnfrmPass, coursename, courseDes;
    ProgressDialog progressDialog;
    Uri uri;
    private static final int IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_instructor);
        progressDialog = new ProgressDialog(Sign_Up_Instructor.this);
        progressDialog.setMessage("Creating your account");
        progressDialog.

        select_image = findViewById(R.id.select_image);
        profile_image = findViewById(R.id.profile_image);
        name = findViewById(R.id.name_et);
        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        cnfrmPass = findViewById(R.id.confirm_password_et);
        courseDes = findViewById(R.id.coursedes_et);
        coursename = findViewById(R.id.coursename_et);

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
                Date d = new Date();
                if (validate()){
                    progressDialog.show();
                    Constants.auth().createUserWithEmailAndPassword(
                            email.getText().toString(),
                            password.getText().toString()
                    ).addOnSuccessListener(authResult -> {
                        Constants.storageReference(authResult.getUser().getUid())
                                .child("logo").child(authResult.getUser().getUid() + d.getTime())
                                .putFile(uri).addOnSuccessListener(taskSnapshot -> {
                                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                                        UserModel model = new UserModel(
                                                name.getText().toString(),
                                                email.getText().toString(),
                                                password.getText().toString(),
                                                coursename.getText().toString(),
                                                courseDes.getText().toString(),
                                                uri.toString(),
                                                true
                                        );
                                        Constants.databaseReference().child("users")
                                                .child(authResult.getUser().getUid())
                                                .setValue(model)
                                                .addOnSuccessListener(unused -> {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(Sign_Up_Instructor.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                                    Intent homeIntent = new Intent(Sign_Up_Instructor.this, MainActivity.class);
                                                    startActivity(homeIntent);
                                                    Animatoo.animateFade(Sign_Up_Instructor.this);
                                                    finish();
                                                }).addOnFailureListener(e -> {

                                                    progressDialog.dismiss();
                                                    Toast.makeText(Sign_Up_Instructor.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                });
                                    });
                                })
                                .addOnFailureListener(e -> {

                                    progressDialog.dismiss();
                                    Toast.makeText(Sign_Up_Instructor.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(Sign_Up_Instructor.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    private boolean validate() {
        if (name.getText().toString().isEmpty()){
            name.setError("Name is Required");
            name.requestFocus();
            return false;
        }
        if (email.getText().toString().isEmpty()){
            email.setError("Email is Required");
            email.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            email.setError("Please Provide valid email*");
            email.requestFocus();
            return false;
        }
        if (password.getText().toString().isEmpty()){
            password.setError("Password is Required");
            password.requestFocus();
            return false;
        }
        if (password.getText().toString().length() < 8){
            password.setError("Password Should be Minimum 8 digits long");
            password.requestFocus();
            return false;
        }
        if (cnfrmPass.getText().toString().isEmpty()){
            cnfrmPass.setError("Password is Required");
            cnfrmPass.requestFocus();
            return false;
        }
        if (!cnfrmPass.getText().toString().equals(password.getText().toString())){
            cnfrmPass.setError("Password is not match");
            cnfrmPass.requestFocus();
            return false;
        }
        if (coursename.getText().toString().isEmpty()){
            coursename.setError("Course Name is Required");
            coursename.requestFocus();
            return false;
        }
        if (courseDes.getText().toString().isEmpty()){
            courseDes.setError("Course Description is Required");
            courseDes.requestFocus();
            return false;
        }
        if (uri == null){
            Toast.makeText(this, "Please Upload an image", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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