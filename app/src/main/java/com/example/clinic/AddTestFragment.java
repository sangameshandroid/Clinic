package com.example.clinic;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTestFragment extends Fragment {

    private EditText edit_test_name, edit_test_descriptions;
    private Button btn_add_test;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private TestAdapter testAdapter;





    public AddTestFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_add_test, container, false);

       edit_test_name = view.findViewById(R.id.edit_test_name);
       edit_test_descriptions = view.findViewById(R.id.edit_test_descriptions);
        btn_add_test = view.findViewById(R.id.btn_add_test);

        recyclerView = view.findViewById(R.id.test_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseReference = FirebaseDatabase.getInstance().getReference("AddTests");
        testAdapter = new TestAdapter(databaseReference);
        recyclerView.setAdapter(testAdapter);

        btn_add_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Test Added Successfully", Toast.LENGTH_SHORT).show();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("AddTests");

                if (edit_test_name != null){
                    String testname = edit_test_name.getText().toString();
                    String testdescriptions = edit_test_descriptions.getText().toString();

                    TestData testData = new TestData(testname, testdescriptions);
                    reference.push().setValue(testData);


                }






            }
        });

        return view;
    }
}