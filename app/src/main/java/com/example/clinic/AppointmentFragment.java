package com.example.clinic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppointmentFragment extends Fragment {
    private Button btn_new_appointment;

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private AppointmentAdapter appointmentAdapter;


    public AppointmentFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_appointment, container, false);

        btn_new_appointment = view.findViewById(R.id.btn_new_appointment);
        recyclerView = view.findViewById(R.id.appointment_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("Appointment");
        appointmentAdapter = new AppointmentAdapter(databaseReference);
        recyclerView.setAdapter(appointmentAdapter);

        btn_new_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                Fragment fragment = new NewAppointmentFragment();
                fragment.setArguments(bundle);
                FragmentTransaction ft = ((FrontOfficeActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();


            }
        });

        return view;
    }
}