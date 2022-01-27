package com.gautham.learner.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gautham.learner.Mentoruser;
import com.gautham.learner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the    factory method to
 * create an instance of this fragment.
 */
public class MprofileFragment extends Fragment {


    public MprofileFragment() {
        // Required empty public constructor
    }
    FirebaseUser fuser;
    DatabaseReference reference;
    TextView mmmname,lic,sub;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mprofile, container, false);
        Button post=view.findViewById(R.id.post);
        mmmname=view.findViewById(R.id.Mbhjusernamesss);
        lic=view.findViewById(R.id.Schoolicensss);
        sub=view.findViewById(R.id.teachingsubjectsss);
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Mentoruser").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Mentoruser mm=snapshot.getValue(Mentoruser.class);
                mmmname.setText(mm.getMentorname());
                lic.setText(mm.getSchoollicense());
                sub.setText(mm.getTeachingsubjects());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        post.setOnClickListener(new View.OnClickListener() {

           // private Object BlogwritingFragment;



            @Override
            public void onClick(View view) {
               Fragment fragment = new BlogwritingFragment();
                //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
              //  getSupportFragmentManager.BeginTransaction().Replace(Resource.Id.content, fragmentB).Commit();
                 //view=inflater.inflate(R.layout.fragment_blogwriting, container, false);
            }
        });


      return view;
    }
}