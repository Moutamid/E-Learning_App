package com.moutamid.e_learningapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.moutamid.e_learningapp.Actvities.Display_Activity;
import com.moutamid.e_learningapp.Actvities.Sign_Up_Activity;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.Models.Model_Courses;
import com.moutamid.e_learningapp.R;

import java.util.ArrayList;
import java.util.Collection;

public class Adapter_Courses extends RecyclerView.Adapter<Adapter_Courses.HolderAndroid> implements Filterable {

    private Context context;
    private ArrayList<Model_Courses> androidArrayList;
    ArrayList<Model_Courses> listAll;

    public Adapter_Courses(Context context, ArrayList<Model_Courses> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
        listAll = new ArrayList<>(androidArrayList);
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

        String image_1 = modelAndroid.getImage();

        holder.title.setText(title_tv);
        holder.tutor.setText(tutor_tv);
        holder.member.setText(member_tv);
        holder.efficient.setText(eff_tv);
        holder.price.setText(price_tv);
        holder.status.setText(status_tv);
        holder.time.setText(time_tv);
        holder.desc.setText(des_tv);

        Glide.with(context).load(modelAndroid.getImage()).into(holder.image);

        holder.enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.auth().getCurrentUser() != null){
                    Intent intent = new Intent(context , Display_Activity.class);
                    context.startActivity(intent);
                    Animatoo.animateFade(context);
                } else {
                    Intent intent = new Intent(context , Sign_Up_Activity.class);
                    context.startActivity(intent);
                    Animatoo.animateFade(context);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Model_Courses> filterList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filterList.addAll(listAll);
            } else {
                for (Model_Courses listModel : listAll){
                    if (listModel.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterList.add(listModel);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        //run on Ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            androidArrayList.clear();
            androidArrayList.addAll((Collection<? extends Model_Courses>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image ;
        private TextView title , tutor , member , efficient , price , status , time , desc;
        private CardView card_enroll;
        Button enroll;

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
            enroll = itemView.findViewById(R.id.btn_enroll);
            card_enroll = itemView.findViewById(R.id.card_enroll);

        }
    }
}
