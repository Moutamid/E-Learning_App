package com.moutamid.e_learningapp.Actvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.moutamid.e_learningapp.Constants;
import com.moutamid.e_learningapp.R;

public class Forget_Password extends AppCompatActivity {

    Button recoverBtn;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email = findViewById(R.id.email_et_forget);
        recoverBtn = findViewById(R.id.recoverBtn);
        recoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    Constants.auth().sendPasswordResetEmail(email.getText().toString())
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(getApplicationContext(), "Check Your Email", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Forget_Password.this , Login_Activity.class);
                                Animatoo.animateFade(Forget_Password.this);
                                startActivity(intent);
                            }).addOnFailureListener(e -> {
                                Toast.makeText(Forget_Password.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });
    }

    private boolean validate() {
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
        return true;
    }
}