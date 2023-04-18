package com.example.clinic;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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




        checkFeild(login_email);
        checkFeild(login_password);

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginemail = login_email.getText().toString();
                String loginpassword = login_password.getText().toString();



                if (loginemail.isEmpty() || loginpassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(loginemail, loginpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String uid = task.getResult().getUser().getUid();
                            DatabaseReference userref = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
                            userref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String usertype = snapshot.child("usertype").getValue(String.class);
                                    if (usertype != null) {
                                        switch (usertype) {
                                            // switch cases here

                                            case "Admin":
                                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                break;
                                            case "Doctor":
                                                Intent intent1 = new Intent(LoginActivity.this, DoctorAdminActivity.class);
                                                startActivity(intent1);
                                                break;
                                            case "Front - Office":
                                                Intent intent2 = new Intent(LoginActivity.this, FrontOfficeActivity.class);
                                                startActivity(intent2);
                                                break;
                                            default:
                                                Toast.makeText(LoginActivity.this, "Invalid User Type", Toast.LENGTH_SHORT).show();
                                                break;
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
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