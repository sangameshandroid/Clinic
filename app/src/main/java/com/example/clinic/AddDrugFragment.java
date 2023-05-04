package com.example.clinic;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class AddDrugFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner tradenamespinner, genericnamespinner;
    private EditText drugs_note, drugs_id;
    private Button btn_drugs_add;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private DrugsAdapter drugsAdapter;






    public AddDrugFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_drug, container, false);

        tradenamespinner = view.findViewById(R.id.tradenamespinner);
        genericnamespinner = view.findViewById(R.id.genericnamespinner);
        drugs_note = view. findViewById(R.id.drugs_note);
        btn_drugs_add = view.findViewById(R.id.btn_drugs_add);
        drugs_id = view.findViewById(R.id.drugs_id);

        recyclerView = view.findViewById(R.id.drugs_recycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("DrugsList");
        drugsAdapter = new DrugsAdapter(databaseReference);
        recyclerView.setAdapter(drugsAdapter);








        btn_drugs_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "Drug Is Added", Toast.LENGTH_SHORT).show();
                String tradename = tradenamespinner.getSelectedItem().toString();
                String genericname =  genericnamespinner.getSelectedItem().toString();

                String note = drugs_note.getText().toString();
                String id = drugs_id.getText().toString();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference().child("DrugsList");

                DrugsList drugsList = new DrugsList(tradename, genericname, note, id);
                String drugId = reference.push().getKey();
                reference.child(drugId).setValue(drugsList).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });


            }
        });
//Trade Name
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getContext(), R.array.TradeName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tradenamespinner.setAdapter(adapter);

        tradenamespinner.setOnItemSelectedListener(this);

        //Generic Name
        ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(getContext(), R.array.GenericName, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genericnamespinner.setAdapter(adapter);

        genericnamespinner.setOnItemSelectedListener(this);




        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}