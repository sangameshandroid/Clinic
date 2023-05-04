package com.example.clinic;

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

public class PatientViewFragment extends Fragment {

    TextView txt_firstn, txt_lastn, txt_email,txt_phone, txt_dob, txt_married, txt_sex, txt_blood, txt_weight,
            txt_height, address, txt_phistory;
    ;

    public PatientViewFragment() {
        // Required empty public constructor
    }


    public static PatientViewFragment newInstance(String param1, String param2) {
        PatientViewFragment fragment = new PatientViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_patient_view, container, false);







        return view;
    }
}