package com.example.clinic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class Patientregistration extends Fragment implements AdapterView.OnItemSelectedListener {
    EditText patient_fname, patient_lname, patient_email, patient_mobile, patient_date, patient_weight, patient_height, patient_address, patient_history, patient_period;
    Spinner genderspinner, martialstatusspinner, bloodgroupspinner, knowndiseasesspinner, familyhistoryzspinner, diseasesspinner;

    ImageView patient_img;
    TextView patient_img_upload;
    Button patient_register_btn;

    private static final int PICK_IMAGE_CODE = 1000;

    StorageReference storageReference;

    FirebaseFirestore fStore;
    Uri filePath;
    Bitmap bitmap;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;




    public Patientregistration() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_CODE){
                UploadTask uploadTask = storageReference.putFile(data.getData());

                Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            String url = task.getResult().toString().substring(0, task.getResult().toString().indexOf("token"));
                            Log.d("DIRECTLINK", url);

                            Picasso.get().load(url).into(patient_img);



                        }
                    }
                });

            }

        }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_patientregistration, container, false);

        patient_fname = view.findViewById(R.id.patient_fname);
        patient_lname = view.findViewById(R.id.patient_lname);
        patient_email = view.findViewById(R.id.patient_email);
        patient_mobile = view.findViewById(R.id.patient_mobile);
        patient_date = view.findViewById(R.id.pastient_date);
        patient_weight = view.findViewById(R.id.patient_weight);
        patient_height = view.findViewById(R.id.patient_height);
        patient_address = view.findViewById(R.id.patient_address);
        patient_history = view.findViewById(R.id.patient_history);
        patient_period = view.findViewById(R.id.patient_period);
        patient_img = view.findViewById(R.id.patient_img);
        patient_img_upload = view.findViewById(R.id.patient_img_upload);
        patient_register_btn = view.findViewById(R.id.patient_register_btn);

        //Spinner
        genderspinner = view.findViewById(R.id.genderspinner);
        martialstatusspinner = view.findViewById(R.id.martialstatusspinner);
        bloodgroupspinner = view.findViewById(R.id.bloodgroupspinner);
        knowndiseasesspinner = view.findViewById(R.id.knowndiseasesspinner);
        familyhistoryzspinner = view.findViewById(R.id.familyhistoryzspinner);
        diseasesspinner = view.findViewById(R.id.diseasesspinner);


        //Logic


        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("image_upload");


        patient_img_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Select Picture"), PICK_IMAGE_CODE);

            }
        });

        patient_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = patient_fname.getText().toString();
                String lname = patient_lname.getText().toString();
                String email = patient_email.getText().toString();
                String mobile = patient_mobile.getText().toString();
                String date = patient_date.getText().toString();
                String weight = patient_weight.getText().toString();
                String height = patient_height.getText().toString();
                String address = patient_address.getText().toString();
                String history = patient_history.getText().toString();
                String period = patient_period.getText().toString();
//
                String gender = genderspinner.getSelectedItem().toString();
                String martalstatus = martialstatusspinner.getSelectedItem().toString();
                String bloodgroup = bloodgroupspinner.getSelectedItem().toString();
                String knowndiseases = knowndiseasesspinner.getSelectedItem().toString();
                String familyhistory = familyhistoryzspinner.getSelectedItem().toString();
                String diseases = diseasesspinner.getSelectedItem().toString();

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("patientinformation");

                Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                Patientholder pholder = new Patientholder(fname,lname,email,mobile,date,weight, height,address,history,period,
                            gender,martalstatus,bloodgroup,knowndiseases,familyhistory,diseases);
                    reference.push().setValue(pholder);

                }





        });



        //Setting of Spinner

        //Gender Spinner
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getContext(), R.array.ChoosePatientGender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(adapter);

        genderspinner.setOnItemSelectedListener(this);

        //Martial Status Spinner
        ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(getContext(), R.array.MartialStatus, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        martialstatusspinner.setAdapter(adapter1);

        martialstatusspinner.setOnItemSelectedListener(this);

        //Blood Group Spinner
        ArrayAdapter<CharSequence> adapter2= ArrayAdapter.createFromResource(getContext(), R.array.BloodGroup, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodgroupspinner.setAdapter(adapter2);

        bloodgroupspinner.setOnItemSelectedListener(this);

        //Known Disesaes Spinner
        ArrayAdapter<CharSequence> adapter3= ArrayAdapter.createFromResource(getContext(), R.array.KnownDiseases, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        knowndiseasesspinner.setAdapter(adapter3);

        knowndiseasesspinner.setOnItemSelectedListener(this);

        //Family History
        ArrayAdapter<CharSequence> adapter4= ArrayAdapter.createFromResource(getContext(), R.array.FamilyHistory, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyhistoryzspinner.setAdapter(adapter4);

        familyhistoryzspinner.setOnItemSelectedListener(this);

        //Diseases
        ArrayAdapter<CharSequence> adapter6= ArrayAdapter.createFromResource(getContext(), R.array.Diseases, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diseasesspinner.setAdapter(adapter6);

        diseasesspinner.setOnItemSelectedListener(this);


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String gender = adapterView.getItemAtPosition(i).toString();
        String martialstatus = adapterView.getItemAtPosition(i).toString();
        String bloodgroup = adapterView.getItemAtPosition(i).toString();
        String knowndiseases = adapterView.getItemAtPosition(i).toString();
        String familyhistory = adapterView.getItemAtPosition(i).toString();
        String diseases = adapterView.getItemAtPosition(i).toString();






    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}