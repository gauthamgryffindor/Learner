package com.gautham.learner;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Blogshowing extends AppCompatActivity {
    RecyclerView rv;
    Intent intent;
    String userid,a;

    private BlogAdapter blogAdapter;
    private List<Blog> blogss=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogshowing);
        rv=findViewById(R.id.recyclerview1);
        intent = getIntent();
        userid = intent.getStringExtra("id");
        rv.setHasFixedSize(true);
        System.out.println("test");
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(l);

        DatabaseReference dw= FirebaseDatabase.getInstance().getReference("Mentoruser").child(userid);

        dw.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshotq) {

                Mentoruser g = snapshotq.getValue(Mentoruser.class);
                a=g.getId();
                System.out.println(a);
                DatabaseReference d= FirebaseDatabase.getInstance().getReference("Blog");
                d.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot snapshots:snapshot.getChildren()) {
                            Blog g1 = snapshots.getValue(Blog.class);
                            System.out.println(a);
                            System.out.println(g1.getId());
                            if (a.equals( g1.getId() )) {

                                blogss.add(g1);
                            }

                        }

                        blogAdapter=new BlogAdapter(getApplicationContext(),blogss);
                        rv.setAdapter(blogAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
