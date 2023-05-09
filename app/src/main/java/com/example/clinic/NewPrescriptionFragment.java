package com.example.clinic;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;

public class NewPrescriptionFragment extends Fragment {

    private Spinner prescription_patient_name, selectdrugspinner, selecttestsspinner;
    EditText drug_type, medicine_mg, medicine_dose, medicine_duration, medicine_comment, test_discriptions;
    private Button btn_create_prescription;

    DatabaseReference reference;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;

    FirebaseDatabase rootNode;
    private PrintPrescriptionFragment printPrescriptionFragment = null;




    public NewPrescriptionFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_new_prescription, container, false);

        prescription_patient_name = view.findViewById(R.id.prescription_patient_name);
        prescription_patient_name.setPrompt("Choose Patient Name");
        selectdrugspinner = view.findViewById(R.id.selectdrugspinner);
        selectdrugspinner.setPrompt("Choose Drugs");

        selecttestsspinner = view.findViewById(R.id.selecttestsspinner);
        selecttestsspinner.setPrompt("Choose Tests");

        medicine_mg = view.findViewById(R.id.medicine_mg);
        medicine_dose = view.findViewById(R.id.medicine_dose);
        medicine_duration = view.findViewById(R.id.medicine_duration);
        medicine_comment = view.findViewById(R.id.medicine_comment);
        test_discriptions = view.findViewById(R.id.test_discri);

        btn_create_prescription = view.findViewById(R.id.btn_create_prescriptions);

        prescription_patient_name.setFocusable(true);


        btn_create_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String patientname = prescription_patient_name.getSelectedItem().toString();

                String druglist = selectdrugspinner.getSelectedItem().toString();
                String bloodtest = selecttestsspinner.getSelectedItem().toString();
                String ml = medicine_mg.getText().toString();
                String dose = medicine_dose.getText().toString();
                String duration = medicine_duration.getText().toString();
                String comment = medicine_comment.getText().toString();
                String testdiscription = test_discriptions.getText().toString();


                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference().child("PatientPrescriptions");

                String key = reference.push().getKey(); // generate unique key


                PrescriptionsClass prescriptionsClass = new PrescriptionsClass(patientname,druglist, bloodtest, ml, dose, duration, comment, testdiscription);

                reference.child(key).setValue(prescriptionsClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Appointment Set Successfully", Toast.LENGTH_SHORT).show();



                        printPrescriptionFragment = new PrintPrescriptionFragment();
                        Bundle args = new Bundle();
                        args.putString("patientName", patientname);
                        args.putString("drugList", druglist);
                        args.putString("medicineMG", ml);
                        args.putString("medicineDose", dose);
                        args.putString("medicineDuration", duration);
                        args.putString("docotorComment", comment);
                        args.putString("testType", bloodtest);
                        args.putString("testDiscriptions", testdiscription);









                        printPrescriptionFragment.setArguments(args);

                        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, printPrescriptionFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();





                    }
                });










            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference patientRef = database.getReference("patientinformation");
        DatabaseReference drugRef = database.getReference("DrugsList");
        DatabaseReference testRef = database.getReference("AddTests");

        testRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> prescriptiontest = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TestData testData = snapshot.getValue(TestData.class);
                    prescriptiontest.add(testData.getTestdataName());


                }
                //test list
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item, prescriptiontest);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                selecttestsspinner.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        drugRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> prescriptiondrugList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Drugs drugs = snapshot.getValue(Drugs.class);
                    prescriptiondrugList.add(drugs.getTradeName());

                }
                //drug list
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item, prescriptiondrugList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                selectdrugspinner.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        patientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> patientprescriptionsNames = new ArrayList<>();


                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Patient patient = snapshot.getValue(Patient.class);
                    patientprescriptionsNames.add(patient.getFname());

                }
                //Patient name
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item, patientprescriptionsNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                prescription_patient_name.setAdapter(adapter);
                prescription_patient_name.setSelection(0);









            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return view;
    }

}