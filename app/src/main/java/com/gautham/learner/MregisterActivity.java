package com.gautham.learner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MregisterActivity extends AppCompatActivity {
    EditText mentorname,memail,mpass,mcpass,school;
    RadioButton mtamil,menglish,mmaths,mscience,msocial;
    FirebaseAuth auth;
    Button mregister;
    DatabaseReference myref;
    String mname,mail,mpas,mconf,scl,teaching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mregister);
        auth=FirebaseAuth.getInstance();
        mentorname=findViewById(R.id.Mentorname);
        memail=findViewById(R.id.Mtemail);
        mpass=findViewById(R.id.Mtpass);
        mcpass=findViewById(R.id.Mtconfirm);
        school=findViewById(R.id.SclNumber);
        mtamil=findViewById(R.id.radioButton1);
        menglish=findViewById(R.id.radioButton2);
        mmaths=findViewById(R.id.radioButton3);
        mscience=findViewById(R.id.radioButton4);
        msocial=findViewById(R.id.radioButton5);
        mregister=findViewById(R.id.Mentregister);
        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mname=mentorname.getText().toString().trim();
                mail=memail.getText().toString().trim();
                mpas=mpass.getText().toString().trim();
                mconf=mcpass.getText().toString().trim();
                scl=school.getText().toString().trim();
                if(mtamil.isChecked()){
                    teaching="tamil";
                }
                if(menglish.isChecked()){
                    teaching="english";
                }
                if(mmaths.isChecked()){
                    teaching="maths";
                }
                if(mscience.isChecked()){
                    teaching="science";
                }
                if(msocial.isChecked()){
                    teaching="social";
                }
                if(mname.isEmpty()||mail.isEmpty()||mpas.isEmpty()||mconf.isEmpty()||scl.isEmpty()||teaching.isEmpty()){
                    Toast.makeText(MregisterActivity.this,"enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean n=mpas.equals(mconf);
                    if (n == false) {
                        Toast.makeText(MregisterActivity.this, "password is not matching", Toast.LENGTH_LONG).show();
                    } else {

                        //Iterables.removeIf(subs, Predicates.isNull());
                        Registernow(mname,mail,mpas,teaching,scl);
                    }
                }
            }
        });
    }
    private  void  Registernow(final String mename,String meemail,String mpassw,String teachsub,String scls){
        auth.createUserWithEmailAndPassword(meemail,mpassw).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                FirebaseUser mentor=auth.getCurrentUser();
                mentor.sendEmailVerification().addOnSuccessListener(aVoid -> Toast.makeText(MregisterActivity.this,"email verification has been sent",Toast.LENGTH_SHORT).show()).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: email not sent "+e.getMessage());
                    }
                });
                //int q=1;
                String muserid=mentor.getUid();
                myref=FirebaseDatabase.getInstance().getReference("Mentoruser")
                        .child(muserid);
                HashMap<String,String> hash=new HashMap<>();
                hash.put("mentorname",mename);
                hash.put("schoollicense",scls);
                hash.put("teachingsubjects",teachsub);
                hash.put("id",muserid);
                hash.put("type","1");
                myref.setValue(hash).addOnCompleteListener(task1 -> {

                    if(task1.isSuccessful()) {
                        Intent i1 = new Intent(MregisterActivity.this, MainActivity3.class);
                        i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(i1);

                        finish();
                    }else{
                        Toast.makeText(MregisterActivity.this,"registeration not done error occurred",Toast.LENGTH_SHORT).show();

                    }
                });

            }else{
                Toast.makeText(MregisterActivity.this,"invalid email or password",Toast.LENGTH_SHORT).show();
            }
            });
    }
}