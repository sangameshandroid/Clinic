package com.example.clinic;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorViewFragment extends Fragment {
    TextView dtxt_firsname, dtxt_lastname, dtxt_email, dtxt_phone, dtxt_dob, dtxt_sex, dtxt_city, dtxt_nationality, dtxt_languages,
            dtxt_zip, dtxt_specilization, dtxt_address, dtxt_phistory;



    public DoctorViewFragment() {
        // Required empty public constructor
    }


    public static DoctorViewFragment newInstance(String param1, String param2) {
        DoctorViewFragment fragment = new DoctorViewFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_doctor_view, container, false);

        dtxt_firsname = view.findViewById(R.id.dtxt_firstn);
        dtxt_lastname = view.findViewById(R.id.dtxt_lastn);
        dtxt_email = view.findViewById(R.id.dtxt_email);
        dtxt_phone = view.findViewById(R.id.dtxt_phone);
        dtxt_dob = view.findViewById(R.id.dtxt_dob);
        dtxt_sex = view.findViewById(R.id.dtxt_sex);
        dtxt_city = view.findViewById(R.id.dtxt_city);
        dtxt_nationality = view.findViewById(R.id.dtxt_nationality);
        dtxt_languages = view.findViewById(R.id.dtxt_languages);
        dtxt_zip = view.findViewById(R.id.dtxt_zip);
        dtxt_specilization = view.findViewById(R.id.dtxt_specilization);
        dtxt_address = view.findViewById(R.id.dtxt_address);
        dtxt_phistory = view.findViewById(R.id.dtxt_phistory);

        String uid = getArguments().getString("uid");

        if (uid != null) {


            DatabaseReference dr = FirebaseDatabase.getInstance().getReference("users").child(uid);
            dr.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Doctor doctor = snapshot.getValue(Doctor.class);

                    assert doctor != null;
                    dtxt_firsname.setText(doctor.getFirstname());


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



        return view;

    }
}