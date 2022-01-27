package com.gautham.learner.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gautham.learner.R;
import com.gautham.learner.Studentsuser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.HashMap;

public class SFragmentprofile extends Fragment {

    TextView name,subject;
    Button update;
    String subtss="q";
    DatabaseReference reference;
    FirebaseUser Suser;
    RadioButton tamil,english,maths,science,social;
    public SFragmentprofile() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_s_fragmentprofile, container, false);
        Suser=FirebaseAuth.getInstance().getCurrentUser();
        name=view.findViewById(R.id.Sbhjusernamesss);
        subject=view.findViewById(R.id.Subfghrhfhr);
        tamil=view.findViewById(R.id.radioButton001);
        english=view.findViewById(R.id.radioButton002);
        maths=view.findViewById(R.id.radioButton003);
        science=view.findViewById(R.id.radioButton004);
        social=view.findViewById(R.id.radioButton005);
        update=view.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tamil.isChecked()){
                    subtss="tamil";
                }
                if(english.isChecked()){
                    subtss="english";

                }
                if(maths.isChecked()){
                    subtss="maths";

                }
                if(science.isChecked()){
                    subtss="science";

                }
                if(social.isChecked()){
                    subtss="social";

                }
                if(subtss=="q"){
                    Toast.makeText(getActivity(),"select the subject and press",Toast.LENGTH_SHORT).show();
                }else{

                    reference= FirebaseDatabase.getInstance().getReference("Studentsuser").child(Suser.getUid());
                    HashMap<String,String> hp=new HashMap<>();
                    hp.put("subjects",subtss);
                    reference.updateChildren(Collections.unmodifiableMap(hp));
                }

            }
        });
        reference= FirebaseDatabase.getInstance().getReference("Studentsuser").child(Suser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Studentsuser  mm=snapshot.getValue(Studentsuser.class);
                name.setText(mm.getStudentname());

                subject.setText(mm.getSubjects());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}