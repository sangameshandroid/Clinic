package com.example.clinic;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    Spinner chooseuserpinner;
    Button signin_btn;
    EditText login_email, login_password;
    FirebaseAuth firebaseAuth ;

    TextView txt;
    ProgressBar progressBar;

    boolean valid = true;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin_btn = findViewById(R.id.login_btn);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        firebaseAuth = FirebaseAuth.getInstance();
        txt = findViewById(R.id.txt);


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });




        checkFeild(login_email);
        checkFeild(login_password);



        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String username = login_email.getText().toString();
                String password = login_password.getText().toString();
                ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("Signing in...");
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(false);

                View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.custom_progressbar, null);
                progressDialog.setView(view);


                ProgressBar progressBar = view.findViewById(R.id.progressBar);

                progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00C0FF")));




                progressDialog.show();

                mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            String uid = task.getResult().getUser().getUid();
                            DatabaseReference userref = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                            userref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String usertype = snapshot.child("usertype").getValue(String.class);
                                    if (usertype != null) {

                                        switch (usertype) {
                                            case "Doctor":
                                                String firstname = snapshot.child("firstname").getValue(String.class);
                                                String lastname = snapshot.child("lastname").getValue(String.class);
                                                String specialization = snapshot.child("specialization").getValue(String.class);
                                                String imageuri = snapshot.child("imageurl").getValue(String.class);
                                                Intent intent = new Intent(LoginActivity.this, DoctorAdminActivity.class);
                                                intent.putExtra("first", firstname);
                                                intent.putExtra("last", lastname);
                                                intent.putExtra("special", specialization);
                                                intent.putExtra("imageuri", imageuri);
                                                startActivity(intent);
                                                break;
                                            case "Admin":
                                                String first = snapshot.child("firstname").getValue(String.class);
                                                String last = snapshot.child("lastname").getValue(String.class);
                                                String image = snapshot.child("imageurl").getValue(String.class);
                                                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                                                intent1.putExtra("firstname", first);
                                                intent1.putExtra("lastname", last);
                                                intent1.putExtra("image", image);
                                                startActivity(intent1);
                                                break;
                                            case "Front - Office":
                                                String firstName = snapshot.child("firstname").getValue(String.class);
                                                String lastName = snapshot.child("lastname").getValue(String.class);
                                                String Image = snapshot.child("imageurl").getValue(String.class);
                                                Intent intent2 = new Intent(LoginActivity.this, FrontOfficeActivity.class);
                                                intent2.putExtra("First", firstName);
                                                intent2.putExtra("Last", lastName);
                                                intent2.putExtra("Image", Image);
                                                startActivity(intent2);
                                                break;
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }else{
                            Toast.makeText(LoginActivity.this, "failed to sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });









    /*    ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.ChooseUserType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseuserpinner.setAdapter(adapter);

        chooseuserpinner.setOnItemSelectedListener(this);*/




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

   /* @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String choosegender = adapterView.getItemAtPosition(i).toString();



        Toast.makeText(getApplicationContext(), choosegender, Toast.LENGTH_SHORT).show();
        // Clear login credentials when user switches user type
        login_email.setText("");
        login_password.setText("");


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}