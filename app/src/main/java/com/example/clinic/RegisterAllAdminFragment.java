package com.example.clinic;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


@SuppressWarnings("ALL")
public class RegisterAllAdminFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final int PICK_IMAGE_CODE = 1000;
    private Spinner languagesspinner, nationalityspinner, cityspinner, usertypespinner, specilizationspinner, genderspinner;
    Button btn_user_register;
    EditText register_first_name, register_last_name, register_user_email, register_user_mobile, register_user_date,register_user_zip, regsiter_user_id, register_user_password, register_user_address, register_user_bio;
    ImageView img_upload;
    TextView txt_upload;

    boolean valid = true;
    // Firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    Uri filePath;
    Bitmap bitmap;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    ProgressDialog progressDialog;
    StorageReference storageReference;


    public RegisterAllAdminFragment() {
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

                        Picasso.get().load(url).into(img_upload);



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
        View view = inflater.inflate(R.layout.fragment_register_all_admin, container, false);
// spinner
        languagesspinner = view.findViewById(R.id.languagesspinner);
        nationalityspinner = view.findViewById(R.id.nationalityspinner);
        cityspinner = view.findViewById(R.id.cityspinner);
        usertypespinner = view.findViewById(R.id.usertypespinner);
        specilizationspinner = view.findViewById(R.id.specilizationspinner);
        genderspinner = view.findViewById(R.id.genderspinner);
        btn_user_register = view.findViewById(R.id.btn_user_register);

        //textfeild

        register_first_name = view.findViewById(R.id.register_first_name);
        register_last_name = view.findViewById(R.id.register_last_name);
        register_user_email = view.findViewById(R.id.register_user_email);
        register_user_mobile = view.findViewById(R.id.register_user_mobile);
        register_user_date = view.findViewById(R.id.regisiter_user_date);
        register_user_zip = view.findViewById(R.id.register_user_zip);
        regsiter_user_id = view.findViewById(R.id.register_user_id);
        register_user_password = view.findViewById(R.id.register_user_password);
        register_user_address = view.findViewById(R.id.register_user_address);
        register_user_bio = view.findViewById(R.id.register_user_bio);
        img_upload = view.findViewById(R.id.img_upload);
        txt_upload = view.findViewById(R.id.txt_upload);

        //FireBase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference("image_upload");
        txt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Select Picture"), PICK_IMAGE_CODE);

            }
        });




        btn_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = register_first_name.getText().toString();
                String lastname = register_last_name.getText().toString();
                String email = register_user_email.getText().toString();
                String mobile = register_user_mobile.getText().toString();
                String date = register_user_date.getText().toString();
                String zip = register_user_zip.getText().toString();
                String userid = regsiter_user_id.getText().toString();
                String userpassword = register_user_password.getText().toString();
                String address = register_user_address.getText().toString();
                String bio = register_user_bio.getText().toString();
// spinner
                String gender = genderspinner.getSelectedItem().toString();
                String language = languagesspinner.getSelectedItem().toString();
                String nationality = nationalityspinner.getSelectedItem().toString();
                String city = cityspinner.getSelectedItem().toString();
                String usertype = usertypespinner.getSelectedItem().toString();
                String specilization = specilizationspinner.getSelectedItem().toString();



                if (TextUtils.isEmpty(userid)) {
                    Toast.makeText(getContext(), "Please enter a valid user ID", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userpassword)) {
                    Toast.makeText(getContext(), "Please enter a valid password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(userid).matches()) {
                    Toast.makeText(getContext(), "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                String languageSelected = "";
                String genderSelected = "";
                String nationalitySelected = "";
                String citySelected = "";
                String userTypeSelected = "";
                String specializationSelected = "";

// Get the selected values from the spinners
                if (genderspinner != null && genderspinner.getSelectedItem() != null) {
                    genderSelected = genderspinner.getSelectedItem().toString();
                }

                if (languagesspinner != null && languagesspinner.getSelectedItem() != null) {
                    languageSelected = languagesspinner.getSelectedItem().toString();
                }

                if (nationalityspinner != null && nationalityspinner.getSelectedItem() != null) {
                    nationalitySelected = nationalityspinner.getSelectedItem().toString();
                }

                if (cityspinner != null && cityspinner.getSelectedItem() != null) {
                    citySelected = cityspinner.getSelectedItem().toString();
                }

                if (usertypespinner != null && usertypespinner.getSelectedItem() != null) {
                    userTypeSelected = usertypespinner.getSelectedItem().toString();
                }

                if (specilizationspinner != null && specilizationspinner.getSelectedItem() != null) {
                    specializationSelected = specilizationspinner.getSelectedItem().toString();
                }


                String finalSpecializationSelected = specializationSelected;
                String finalUserTypeSelected = userTypeSelected;
                String finalCitySelected = citySelected;
                String finalNationalitySelected = nationalitySelected;
                String finalLanguageSelected = languageSelected;
                String finalGenderSelected = genderSelected;
                firebaseAuth.createUserWithEmailAndPassword(userid, userpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("users");
                        if (usertype != null && !usertype.isEmpty()) { // Check if usertype is not null or empty

                            dataholder dholder = new dataholder(firstname, lastname, email, mobile, date, zip, userid, userpassword, address, bio, finalGenderSelected, finalLanguageSelected, finalNationalitySelected, finalCitySelected, finalUserTypeSelected, finalSpecializationSelected);
                            reference.child(usertype).push().setValue(dholder);
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getContext(), R.array.Languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languagesspinner.setAdapter(adapter);

        languagesspinner.setOnItemSelectedListener(this);

        //Nationality

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.Nationality, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationalityspinner.setAdapter(adapter1);

        nationalityspinner.setOnItemSelectedListener(this);

        //City
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.City, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityspinner.setAdapter(adapter2);

        cityspinner.setOnItemSelectedListener(this);

        //User Type
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.ChooseUserType, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertypespinner.setAdapter(adapter3);

        usertypespinner.setOnItemSelectedListener(this);

        //Specilization

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(getContext(), R.array.Specilization, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specilizationspinner.setAdapter(adapter4);

        specilizationspinner.setOnItemSelectedListener(this);

        //Gender
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(getContext(), R.array.Choosegender, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderspinner.setAdapter(adapter6);

        genderspinner.setOnItemSelectedListener(this);

        return view;
    }

    public boolean checkFeild(EditText textFeild){
        if (textFeild.getText().toString().isEmpty()){
            textFeild.setError("Error");
            valid = false;
        } else {
            valid = true;
        }
        return valid;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String languages = adapterView.getItemAtPosition(i).toString();
        String nationality = adapterView.getItemAtPosition(i).toString();
        String city = adapterView.getItemAtPosition(i).toString();
        String usertype = adapterView.getItemAtPosition(i).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private class READ_EXTERNAL_STORAGE {
    }
}