package com.example.clinic;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AllDoctorsListFragment extends Fragment {

    private RecyclerView doctors_recycler;
    private DoctorAdapter doctorAdapter;
    private List<Doctor>   doctorlist;




    public AllDoctorsListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_doctors_list, container, false);
        doctors_recycler = view.findViewById(R.id.doctors_recycler);
        doctorlist = new ArrayList<>();
        doctors_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        doctorAdapter = new DoctorAdapter(requireContext(), doctorlist);
        doctors_recycler.setAdapter(doctorAdapter);




        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        Query query = databaseReference.orderByChild("usertype").equalTo("Doctor");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()) {
                    String firstname = dataSnapshot.child("firstname").getValue(String.class);
                    String lastname = dataSnapshot.child("lastname").getValue(String.class);
                    String specialization = dataSnapshot.child("specialization").getValue(String.class);
                    String uid = dataSnapshot.child("uid").getValue(String.class);
                    Doctor doctor = new Doctor(firstname, lastname, specialization, uid);

                    if(doctor!=null){
                        doctor.setUid(dataSnapshot.getKey());
                        doctorlist.add(doctor);
                    }

                }
                doctorAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }
}