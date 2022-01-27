package com.gautham.learner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Mainpage extends AppCompatActivity {
    Button students,mentor;
    FirebaseAuth Authser;
    TextView loadi;
    DatabaseReference mref;
    FirebaseUser firebaseuser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseuser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseuser!=null){
           // Intent a=new Intent(Mainpage.this,MainActivity.class);
            //startActivity(a);
            //finish();
            loadi.setVisibility(View.VISIBLE);
            if (students.hasOnClickListeners()) {
                students.setOnClickListener(null);

            }
            if (mentor.hasOnClickListeners()) {
                mentor.setOnClickListener(null);

            }
            mref = FirebaseDatabase.getInstance().getReference("Studentsuser").child(firebaseuser.getUid());
            mref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Studentsuser value = dataSnapshot.getValue(Studentsuser.class);
                    if(value == null){

                        //Toast.makeText(Mainpage.this, "You're Logged in wrong page", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Mainpage.this, MainActivity4.class));
                        finish();

                    }

                    else{
                        startActivity(new Intent(Mainpage.this, MainActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        students=findViewById(R.id.student);
        mentor=findViewById(R.id.mentor);
        loadi=findViewById(R.id.load);
        loadi.setVisibility(View.INVISIBLE);
        students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Mainpage.this,SloginActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

            }
        });
        mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(Mainpage.this,MloginActivity.class);
                //n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(n);

            }
        });
    }
}