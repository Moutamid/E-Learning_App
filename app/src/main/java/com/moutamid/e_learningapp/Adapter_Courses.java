package com.moutamid.e_learningapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;

public class Adapter_Courses extends RecyclerView.Adapter<Adapter_Courses.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Courses> androidArrayList;

    public Adapter_Courses(Context context, ArrayList<Model_Courses> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_courses, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Courses modelAndroid = androidArrayList.get(position);

        String title_tv = modelAndroid.getTitle();
        String tutor_tv = modelAndroid.getTutor();
        String member_tv = modelAndroid.getMember();
        String eff_tv = modelAndroid.getEfficient();
        String price_tv = modelAndroid.getPrice();
        String status_tv = modelAndroid.getStatus();
        String time_tv = modelAndroid.getTime();
        String des_tv = modelAndroid.getDes();

        int image_1 = modelAndroid.getImage();

        holder.title.setText(title_tv);
        holder.tutor.setText(tutor_tv);
        holder.member.setText(member_tv);
        holder.efficient.setText(eff_tv);
        holder.price.setText(price_tv);
        holder.status.setText(status_tv);
        holder.time.setText(time_tv);
        holder.desc.setText(des_tv);

        holder.image.setImageResource(image_1);

        holder.btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , Display_Activity.class);
                context.startActivity(intent);
                Animatoo.animateSlideDown(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image ;
        private TextView title , tutor , member , efficient , price , status , time , desc;
        private TextView btn_enroll;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.course_img);
            title = itemView.findViewById(R.id.course_title);
            tutor = itemView.findViewById(R.id.course_tutor);
            member = itemView.findViewById(R.id.course_member);
            efficient = itemView.findViewById(R.id.course_efficent);
            price = itemView.findViewById(R.id.course_price);
            status = itemView.findViewById(R.id.course_status);
            time = itemView.findViewById(R.id.course_time);
            desc = itemView.findViewById(R.id.course_des);
            btn_enroll = itemView.findViewById(R.id.btn_enroll);

        }
    }
}
