package com.gautham.learner.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.gautham.learner.Mentoruser;
import com.gautham.learner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class BlogwritingFragment extends Fragment {


    DatabaseReference myrefs,mrefss;
    EditText title,blog,link;
    Button Addpost;
    String subss,times;
    String titles,links,blogs;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_blogwriting, container, false);
        title=view.findViewById(R.id.editTextTextPersonName);
        blog=view.findViewById(R.id.editTextTextMultiLine);
        link=view.findViewById(R.id.editTextTextPersonName2);
        Addpost=view.findViewById(R.id.poster);
        Addpost.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
               titles=title.getText().toString();
               blogs=blog.getText().toString();
               links=link.getText().toString().trim();
               if(titles.isEmpty()||blogs.isEmpty()||links.isEmpty()){
                   Toast.makeText(getActivity(),"enter all the fields",Toast.LENGTH_SHORT).show();
               }else{
                   sendblog(titles,blogs,links);
               }
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendblog(String titles, String blogs, String links) {
        FirebaseUser mentors= FirebaseAuth.getInstance().getCurrentUser();
        String muserids=mentors.getUid();
        mrefss = FirebaseDatabase.getInstance().getReference("Mentoruser").child(mentors.getUid());
        mrefss.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Mentoruser mentorss=snapshot.getValue(Mentoruser.class);
                subss=mentorss.getTeachingsubjects();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myrefs= FirebaseDatabase.getInstance().getReference("Blog");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        times=dtf.format(now);
        HashMap<String,String> hash=new HashMap<>();
        hash.put("id",muserids);
        hash.put("title",titles);
        hash.put("blog",blogs);
        hash.put("links",links);
        hash.put("date",times);
        hash.put("subjects",subss);
        myrefs.push().setValue(hash).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              if(task.isSuccessful()){
                  Toast.makeText(getActivity(),"Blog is posted",Toast.LENGTH_SHORT).show();
                  title.getText().clear();
                  blog.getText().clear();
                  link.getText().clear();
              }else{
                  Toast.makeText(getActivity(),"Blog not posted",Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}