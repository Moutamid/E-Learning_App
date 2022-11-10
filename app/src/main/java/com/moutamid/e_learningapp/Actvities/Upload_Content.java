package com.moutamid.e_learningapp.Actvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.Model_Content;
import com.moutamid.e_learningapp.R;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Upload_Content extends AppCompatActivity {

    Button upload_course;
    EditText name, desc;
    CardView select_image, select_video;
    CircleImageView profile_image;
    Model_Content model_content;
    Uri uriImage, uriVideo;
    String imageLink, videoLink;
    ProgressDialog progressDialog, progressDialogV;
    String uuID;
    ArrayAdapter<CharSequence> catgoryAdapter;
    AutoCompleteTextView categories;
    TextInputLayout et_category;

    private static final int IMAGE_REQUEST = 1;
    private static final int Video_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_content);

        select_image = findViewById(R.id.select_image);
        select_video = findViewById(R.id.select_video);
        profile_image = findViewById(R.id.image);
        upload_course = findViewById(R.id.upload_btn);
        name = findViewById(R.id.name_et);
        desc = findViewById(R.id.desc_et);
        categories = findViewById(R.id.CategoryList);
        et_category = findViewById(R.id.etCatagory);

        progressDialog = new ProgressDialog(Upload_Content.this);
        progressDialogV = new ProgressDialog(Upload_Content.this);

        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);

        progressDialogV.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialogV.setMax(100);
        progressDialogV.setCancelable(false);

        catgoryAdapter = ArrayAdapter.createFromResource(this, R.array.categories, R.layout.dropdown_layout);
        catgoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(catgoryAdapter);

        select_video.setOnClickListener(view -> openVideo());
        select_image.setOnClickListener(view -> openImage());

        uuID = UUID.randomUUID().toString();

        upload_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
    }

    private void openImage() {
        ImagePicker.with(this)
                .galleryOnly()
                .crop(600, 300)
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    private void getCourseData() {
        model_content = new Model_Content(
                uuID,
                name.getText().toString(),
                desc.getText().toString(),
                "By ",
                videoLink,
                imageLink,
                et_category.getEditText().getText().toString()
        );
        Constants.databaseReference().child("course_contents").child(Constants.auth().getCurrentUser().getUid())
                .child(uuID).setValue(model_content)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(getApplicationContext(), "Course Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    Intent homeIntent = new Intent(Upload_Content.this, MainActivity.class);
                    startActivity(homeIntent);
                    Animatoo.animateFade(Upload_Content.this);
                    finish();
                }).addOnFailureListener(e -> {
                    Toast.makeText(Upload_Content.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void openVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Video"), Video_REQUEST);
    }

    private String getfiletype(Uri videouri) {
        ContentResolver r = getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
    }

    private void uploadImage() {
        if (uriImage != null){
            progressDialog.setTitle("Uploading Thumbnail...");
            progressDialog.show();
            // Progress Listener for loading
            // percentage on the dialog box
            Constants.storageReference(Constants.auth().getCurrentUser().getUid())
                    .child("Courses").child(uuID).child("image")
                    .putFile(uriImage).addOnSuccessListener(taskSnapshot -> {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                            imageLink = uri.toString();
                            uploadvideo();
                            progressDialog.dismiss();
                        }).addOnFailureListener(e -> {
                            progressDialog.dismiss();
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }).addOnFailureListener(e -> {
                        // Error, Image not uploaded
                        progressDialog.dismiss();
                        Toast.makeText(Upload_Content.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }).addOnProgressListener(taskSnapshot -> {
                        // show the progress bar
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setProgress((int) progress);
                    });
        }
    }

    private void uploadvideo() {
        if (uriVideo != null) {
            progressDialogV.setTitle("Uploading Video...");
            progressDialogV.show();
            Constants.storageReference(Constants.auth().getCurrentUser().getUid())
                    .child("Courses").child(uuID).child("video")
                    .putFile(uriVideo).addOnSuccessListener(taskSnapshot -> {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                            videoLink = uri.toString();
                            getCourseData();
                            progressDialogV.dismiss();
                        }).addOnFailureListener(e -> {
                            progressDialogV.dismiss();
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    }).addOnFailureListener(e -> {
                        // Error, Image not uploaded
                        progressDialogV.dismiss();
                        Toast.makeText(Upload_Content.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }).addOnProgressListener(taskSnapshot -> {
                        // show the progress bar
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setProgress((int) progress);
                    });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Video_REQUEST){
            if (resultCode == RESULT_OK && data != null && data.getData() != null){
                uriVideo = data.getData();
                Toast.makeText(Upload_Content.this, "Video Selected!!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Upload_Content.this, "No Video is Selected...", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (resultCode == RESULT_OK && data != null && data.getData() != null){
                uriImage = data.getData();
                profile_image.setImageURI(uriImage);
            }
            else {
                Toast.makeText(Upload_Content.this, "No Image is Selected...", Toast.LENGTH_SHORT).show();
            }
        }
    }
}