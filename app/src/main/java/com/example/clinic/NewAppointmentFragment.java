package com.example.clinic;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewAppointmentFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private Spinner patientnamespinner,timespinner;
    private EditText dateappointment;
    private Button btn_appointment;

    //
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;



    public NewAppointmentFragment() {
        // Required empty public constructor
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
        View view =  inflater.inflate(R.layout.fragment_new_appointment, container, false);

        patientnamespinner = view.findViewById(R.id.patientnamespinner);
        timespinner = view.findViewById(R.id.timespinner);
        dateappointment = view.findViewById(R.id.date_appointment);
        btn_appointment = view.findViewById(R.id.btn_appointment);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getContext(), R.array.AppointTimings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timespinner.setAdapter(adapter);

        timespinner.setOnItemSelectedListener(this);

        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = dateappointment.getText().toString();
                String timings = timespinner.getSelectedItem().toString();
                String patient = patientnamespinner.getSelectedItem().toString();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference().child("Appointment");

                String key = reference.push().getKey(); // generate unique key



                PatientAppointment patientAppointment = new PatientAppointment(date, timings, patient);

                reference.child(key).setValue(patientAppointment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Appointment Set Successfully", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("patientinformation");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                ArrayList<String> patientNames = new ArrayList<>();
                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    Patient patient = snapshot.getValue(Patient.class);
                    patientNames.add(patient.getFname());

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item, patientNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                patientnamespinner.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());


            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}