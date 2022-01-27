package com.gautham.learner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class MainActivity3 extends AppCompatActivity {
FirebaseAuth authm;
Button verifybt1,resendemail;
FirebaseUser user;
String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        authm = FirebaseAuth.getInstance();
        //FirebaseFirestore fstrore = FirebaseFirestore.getInstance();
        userid = authm.getCurrentUser().getUid();
        user = authm.getCurrentUser();
                if (user.isEmailVerified()) {
                    Intent i = new Intent(MainActivity3.this,MainActivity4.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                }
        resendemail = findViewById(R.id.resendbtn1);

        resendemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!user.isEmailVerified()) {
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity3.this, "email verification has been sent", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: email not sent " + e.getMessage());
                        }

                    });

                }
            }
        });

    }
}