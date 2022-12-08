package com.moutamid.e_learningapp.Fragments;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fxn.stash.Stash;
import com.google.firebase.database.DataSnapshot;
import com.moutamid.e_learningapp.Adapter.Adapter_Content;
import com.moutamid.e_learningapp.Adapter.Adapter_Enrolled;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.CourseIDs;
import com.moutamid.e_learningapp.Models.Model_Content;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;

public class Content_Fragment extends Fragment implements MediaPlayer.OnCompletionListener {

    VideoView vw;
    ArrayList<Integer> videolist = new ArrayList<>();
    int currvideo = 0;
    String course_ID, sellerID;
    boolean isEnrolled = false;
    private final String[] course_title = {"What is Development ?", "What is Android App Development ?", "Why Graphics Designing Course is Important ?", "Benefits of SEO Course ?",};
    private final int[] images1_detail = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.course_img};
    private RecyclerView detail_recycler;
    private ArrayList<Model_Content> modelContentArrayList;
    private Adapter_Content adapter_content;
    LinearLayout lockLayout;
    String videoURL;

    public Content_Fragment() {
    }

    @Override
    public void onPause() {
        vw.pause();
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_, container, false);
        detail_recycler = view.findViewById(R.id.recyclerView_content);
        lockLayout = view.findViewById(R.id.lockLayout);
        // load_detail();

        vw = view.findViewById(R.id.vidvw2);
        vw.setMediaController(new MediaController(getContext()));
        vw.setOnCompletionListener(this);

        course_ID = Stash.getString("ID");
        sellerID = Stash.getString("sellerID");

        Log.d("IDS", course_ID + "  " + sellerID);

        Constants.databaseReference().child("users").child(Constants.auth().getCurrentUser().getUid())
                .child("enrolled").get()
                .addOnSuccessListener(dataSnapshot -> {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        CourseIDs model = ds.getValue(CourseIDs.class);
                        course_ID = model.getID();
                        sellerID = model.getSellerID();
                        if (model.isEnrolled()){
                            isEnrolled = true;
                            lockLayout.setVisibility(View.GONE);
                        } else {
                            isEnrolled = false;
                            lockLayout.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .addOnFailureListener(e -> {

                });

        Log.d("IDS", course_ID + "  " + sellerID);

        Constants.databaseReference().child("course_contents").child(sellerID)
                .child(course_ID)
                .get().addOnSuccessListener(dataSnapshot -> {
                    Model_Content model = dataSnapshot.getValue(Model_Content.class);
                    videoURL = model.getVideo_link();
                })
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                });

        // video name should be in lower case alphabet.
        videolist.add(R.raw.vid);
        if (isEnrolled){
            setVideo(videoURL);
        }

        return view;

    }

    private void setVideo(String videoURL) {
        /*String uriPath
                = "android.resource://"
                + "com.moutamid.e_learningapp/raw" + "/" + integer;*/
        Uri uri = Uri.parse(videoURL);
        vw.setVideoURI(uri);
        vw.start();
    }

    private void load_detail() {
        modelContentArrayList = new ArrayList<>();


        adapter_content = new Adapter_Content(getContext(), modelContentArrayList);
        detail_recycler.setAdapter(adapter_content);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        AlertDialog.Builder obj = new AlertDialog.Builder(getContext());
        obj.setTitle("Playback Finished!");
        obj.setIcon(R.mipmap.ic_launcher);
        MyListener m = new MyListener();
        obj.setPositiveButton("Replay", m);
        obj.setNegativeButton("Next", m);
        obj.setMessage("Want to replay or play next video?");
        obj.show();
    }

    class MyListener implements DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            if (which == -1) {
                vw.seekTo(0);
                vw.start();
            } else {
                ++currvideo;
                if (currvideo == videolist.size())
                    currvideo = 0;
                // setVideo(videolist.get(currvideo));
            }
        }
    }
}