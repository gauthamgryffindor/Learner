package com.gautham.learner.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gautham.learner.MentAdapter;
import com.gautham.learner.Mentoruser;
import com.gautham.learner.R;
import com.gautham.learner.Studentsuser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MentorFragment extends Fragment {
    private RecyclerView rv1;
    private MentAdapter mentAdapter;
    private List<Mentoruser> Ments;
    FirebaseUser user;
    private String qwrf;
    DatabaseReference d;
    public MentorFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi;
        vi = inflater.inflate(R.layout.fragment_mentor, container, false);
        rv1=vi.findViewById(R.id.Reecycler);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getContext()));
        user= FirebaseAuth.getInstance().getCurrentUser();
        Ments=new ArrayList<>();
        d=FirebaseDatabase.getInstance().getReference("Studentsuser").child(user.getUid());
        d.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Ments.clear();
                Studentsuser sw=snapshot.getValue(Studentsuser.class);
                qwrf=sw.getSubjects();

                DatabaseReference dw= FirebaseDatabase.getInstance().getReference("Mentoruser");

                dw.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotq) {

                        for (DataSnapshot snapshots : snapshotq.getChildren()) {
                            Mentoruser g = snapshots.getValue(Mentoruser.class);
                            if (qwrf.equals(g.getTeachingsubjects())) {
                                System.out.println(g.getTeachingsubjects());
                                Ments.add(g);
                            }
                        }

                        System.out.println(Ments);
                        mentAdapter=new MentAdapter(getContext(),Ments);
                        rv1.setAdapter(mentAdapter);
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
        return vi;
    }

    }

