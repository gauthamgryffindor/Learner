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

public class MainActivity2 extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser userid;
    Button rsendemail,verifybt;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        auth = FirebaseAuth.getInstance();
        //FirebaseFirestore fstrore = FirebaseFirestore.getInstance();
        user = auth.getCurrentUser().getUid();
        userid = auth.getCurrentUser();
                    if (userid.isEmailVerified()) {
                        Intent i = new Intent(MainActivity2.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();

                    }
        rsendemail = findViewById(R.id.resendbtn);

            rsendemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!userid.isEmailVerified()) {
                        userid.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity2.this, "email verification has been sent", Toast.LENGTH_SHORT).show();
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
