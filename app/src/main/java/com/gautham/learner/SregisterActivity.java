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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class SregisterActivity extends AppCompatActivity {
    Button register;
    EditText sname,semail,school,password,cpassword;
    RadioButton tamil,english,science,maths,social;
    FirebaseAuth auth;
    int i=0;
    String subts;
    String snames,smail,spass,scon;
    DatabaseReference myref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sregister);
        sname=findViewById(R.id.Studentname);
        semail=findViewById(R.id.Stuemail);
        school=findViewById(R.id.Schoolname);
        password=findViewById(R.id.stupass);
        cpassword=findViewById(R.id.stconfirm);
        tamil=findViewById(R.id.radioButton01);
        english=findViewById(R.id.radioButton02);
        maths=findViewById(R.id.radioButton03);
        science=findViewById(R.id.radioButton04);
        social=findViewById(R.id.radioButton05);
        auth=FirebaseAuth.getInstance();
        register=findViewById(R.id.sturegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snames=sname.getText().toString().trim();
                smail=semail.getText().toString().trim();
                spass=password.getText().toString().trim();
                scon=cpassword.getText().toString().trim();
                if(tamil.isChecked()){
                    subts="tamil";
                }
                if(english.isChecked()){
                    subts="english";

                }
                if(maths.isChecked()){
                    subts="maths";

                }
                if(science.isChecked()){
                    subts="science";

                }
                if(social.isChecked()){
                    subts="social";

                }
                if (snames.isEmpty()||smail.isEmpty()||scon.isEmpty()||spass.isEmpty()|| subts.isEmpty()) {
                    Toast.makeText(SregisterActivity.this, "enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean n=spass.equals(scon);
                    if (n == false) {
                        Toast.makeText(SregisterActivity.this, "password is not matching", Toast.LENGTH_LONG).show();
                    } else {

                        //Iterables.removeIf(subs, Predicates.isNull());
                        Registernow(snames, smail,spass,subts);
                    }

                }


            }
        });
    }
    private void Registernow(final String username,String email, String password,String subts) {

           auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       FirebaseUser student=auth.getCurrentUser();
                       student.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void aVoid) {
                               Toast.makeText(SregisterActivity.this,"email verification has been sent",Toast.LENGTH_SHORT).show();
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Log.d(TAG, "onFailure: email not sent "+e.getMessage());
                           }
                       });
                       String suserid =student.getUid();
                       myref = FirebaseDatabase.getInstance().getReference("Studentsuser")
                               .child(suserid);
                       //HashMap<String, String[]> map = new HashMap<String, String[]>();
                       //map.put("learningsubjects",subs);


                       HashMap<String, String> hashMap = new HashMap<>();
                       hashMap.put("id",suserid);
                       hashMap.put("studentname",username);
                       hashMap.put("subjects",subts);
                       hashMap.put("type","2");
                       myref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {

                          if(task.isSuccessful()) {
                               Intent i1 = new Intent(SregisterActivity.this, MainActivity2.class);
                               i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                               startActivity(i1);

                               finish();
                           }else{
                               Toast.makeText(SregisterActivity.this,"registeration not done error occurred",Toast.LENGTH_SHORT).show();

                           }
                       }

                       });

                   }else{
                       Toast.makeText(SregisterActivity.this,"invalid email or password",Toast.LENGTH_SHORT).show();
                   }
               }
           });

        }
    }
