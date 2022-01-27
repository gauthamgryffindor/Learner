package com.gautham.learner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SloginActivity extends AppCompatActivity {
    Button register, studentlogin;
    EditText studentmail, studentpassword;
    FirebaseAuth auths;
    String studentemail, studentpasss;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slogin);
        register = findViewById(R.id.studentregister);
        studentlogin = findViewById(R.id.studentLogin);
        studentmail = findViewById(R.id.studentemail);
        studentpassword = findViewById(R.id.studentpass);
        auths = FirebaseAuth.getInstance();
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentemail = studentmail.getText().toString();
                studentpasss = studentpassword.getText().toString();
                if (studentpasss.isEmpty() || studentemail.isEmpty()) {
                    Toast.makeText(SloginActivity.this, "enter all the fields", Toast.LENGTH_SHORT).show();
                } else {

                    auths.signInWithEmailAndPassword(studentemail, studentpasss)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        onAuthSuccesss(task.getResult().getUser());

                                    } else {
                                        Toast.makeText(SloginActivity.this, "invalid password or email id", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SloginActivity.this, SregisterActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);


            }
        });
    }

    private void onAuthSuccesss(FirebaseUser user) {

        
        if (user != null) {

            mref = FirebaseDatabase.getInstance().getReference("Studentsuser").child(user.getUid());
            mref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Studentsuser value = dataSnapshot.getValue(Studentsuser.class);
                    if(value == null){

                        Toast.makeText(SloginActivity.this, "You're Logged in wrong page", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SloginActivity.this, MloginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();

                    }else{
                        startActivity(new Intent(SloginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}