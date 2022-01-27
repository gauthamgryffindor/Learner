package com.gautham.learner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.gautham.learner.Models.Mentoruser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MloginActivity extends AppCompatActivity {
Button register2,login2;
EditText mentemail,mentpass;
FirebaseAuth authsm;
DatabaseReference ref;
String mentremail,mentpasss;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlogin);
        login2=findViewById(R.id.Mentorlogin);
        register2=findViewById(R.id.MentorRegister);
        mentemail=findViewById(R.id.Mentormail);
        mentpass=findViewById(R.id.Mentorpass);
        authsm=FirebaseAuth.getInstance();


        login2.setOnClickListener(view -> {
            mentremail = mentemail.getText().toString();
            mentpasss = mentpass.getText().toString();
            if (mentpasss.isEmpty() || mentremail.isEmpty()) {
                Toast.makeText(MloginActivity.this, "enter all the fields", Toast.LENGTH_SHORT).show();
            } else {

                authsm.signInWithEmailAndPassword(mentremail, mentpasss)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                onAuthSuccess(task.getResult().getUser());


                            } else {
                                Toast.makeText(MloginActivity.this, "invalid password or email id", Toast.LENGTH_SHORT).show();
                            }

                        });


            }


        });
        register2.setOnClickListener(view -> {
            Intent i =new Intent(MloginActivity.this,MregisterActivity.class);
            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        });

    }

    private void onAuthSuccess(com.google.firebase.auth.FirebaseUser user) {


        if (user != null) {
            ref = FirebaseDatabase.getInstance().getReference("Mentoruser").child(user.getUid());
            System.out.println(ref);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   Mentoruser value;
                    value = dataSnapshot.getValue(Mentoruser.class);
                    System.out.println(value);
                    if(value == null){

                        Toast.makeText(MloginActivity.this, "You're Logged in wrong page", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MloginActivity.this, SloginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();

                    }else {
                        startActivity(new Intent(MloginActivity.this, MainActivity4.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

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
