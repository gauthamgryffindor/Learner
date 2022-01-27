package com.gautham.learner.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gautham.learner.Blog;
import com.gautham.learner.BlogAdapter;
import com.gautham.learner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class postfragment extends Fragment {
    private RecyclerView rv;
    private BlogAdapter blogAdapter;
    private List<Blog> blogss;
    public postfragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewer=inflater.inflate(R.layout.fragment_postfragment, container, false);
        rv=viewer.findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        blogss=new ArrayList<>();
        Readuserblog();
        return viewer;
    }

    private void Readuserblog() {

        final FirebaseUser f= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference d= FirebaseDatabase.getInstance().getReference("Blog");
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                blogss.clear();
                for(DataSnapshot snapshots:snapshot.getChildren()) {
                    Blog g = snapshots.getValue(Blog.class);
                    if (g.getId().equals(f.getUid())) {
                        blogss.add(g);
                    }
                }

                blogAdapter=new BlogAdapter(getContext(),blogss);
                rv.setAdapter(blogAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}