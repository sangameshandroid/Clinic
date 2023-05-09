package com.example.clinic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PrintPrescriptionFragment extends Fragment {
    TextView print_patient_name, print_drug_type, print_drug, print_drug_intaking, print_drug_dose, print_drug_duration, print_doctor_comments,
            print_test_recommended, print_test_discriptions;


    public PrintPrescriptionFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_print_prescription, container, false);

        print_patient_name = view.findViewById(R.id.print_patient_name);
        print_drug = view.findViewById(R.id.print_drug);
        print_drug_intaking = view.findViewById(R.id.print_drug_intaking);
        print_drug_dose = view.findViewById(R.id.print_drug_dose);
        print_drug_duration = view.findViewById(R.id.print_drug_duration);
        print_doctor_comments = view.findViewById(R.id.print_doctor_comments);
        print_test_recommended = view.findViewById(R.id.print_test_recommended);
        print_test_discriptions = view.findViewById(R.id.print_test_discriptions);



        Bundle args = getArguments();
        if (args != null) {
            String patientName = args.getString("patientName");
            String drugList = args.getString("drugList");
            String medicineMG = args.getString("medicineMG");
            String medicineDose = args.getString("medicineDose");
            String medicineDuration = args.getString("medicineDuration");
            String docotorComment = args.getString("docotorComment");
            String testType = args.getString("testType");
            String testDiscriptions = args.getString("testDiscriptions");


            print_patient_name.setText(patientName);
            print_drug.setText(drugList);
            print_drug_intaking.setText(medicineMG);
            print_drug_dose.setText(medicineDose);
            print_drug_duration.setText(medicineDuration);
            print_doctor_comments.setText(docotorComment);
            print_test_recommended.setText(testType);
            print_test_discriptions.setText(testDiscriptions);

        }

        return view;
    }
}