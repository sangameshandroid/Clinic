package com.example.clinic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterAllAdminFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner languagesspinner, nationalityspinner, cityspinner, usertypespinner, specilizationspinner, genderspinner;
    Button btn_user_register;
    EditText register_first_name, register_last_name, register_user_email, register_user_mobile, register_user_date,register_user_zip, regsiter_user_id, register_user_password, register_user_address, register_user_bio;

    boolean valid = true;
    // Firebase
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;


    public RegisterAllAdminFragment() {
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

        //FireBase
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();



        btn_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFeild(register_first_name);
                checkFeild(register_last_name);
                checkFeild(register_user_email);
                checkFeild(register_user_mobile);
                checkFeild(register_user_date);
                checkFeild(register_user_zip);
                checkFeild(regsiter_user_id);
                checkFeild(register_user_password);
                checkFeild(register_user_address);
                checkFeild(register_user_bio);

                if (valid) {
                    fAuth.createUserWithEmailAndPassword(register_user_zip.getText().toString(), register_user_password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            Toast.makeText(getContext(), "User Account Created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();


                            ///
                            /*userInfo.put("Language", languagesspinner.toString());
                            userInfo.put("Nationality", nationalityspinner.toString());
                            userInfo.put("City", cityspinner.toString());
                            userInfo.put("UserType", usertypespinner.toString());
                            userInfo.put("Gender", genderspinner.toString());
                            userInfo.put("Specilization", specilizationspinner.toString());*/
                            ///

                            userInfo.put("FirstName", register_first_name.getText().toString());
                            userInfo.put("LastName", register_last_name.getText().toString());
                            userInfo.put("Email", register_user_email.getText().toString());
                            userInfo.put("Mobile", register_user_mobile.getText().toString());
                            userInfo.put("DateBirth", register_user_date.getText().toString());
                            userInfo.put("Zip", register_user_zip.getText().toString());
                            userInfo.put("UserId", regsiter_user_id.getText().toString());
                            userInfo.put("UserPassword", register_user_password.getText().toString());
                            userInfo.put("Address", register_user_address.getText().toString());
                            userInfo.put("Bio", register_user_bio.getText().toString());
                            userInfo.put("isUser", "1");

                            df.set(userInfo);




                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "User Account Is not Created", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });



















      /*  btn_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Registered The User Succesfully", Toast.LENGTH_SHORT).show();
            }
        });*/

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
}