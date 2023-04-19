package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner chooseuserpinner;
    Button signin_btn;
    EditText login_email, login_password;

    boolean valid = true;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        chooseuserpinner = findViewById(R.id.chooseuserspinner);
        signin_btn = findViewById(R.id.login_btn);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);


        checkFeild(login_email);
        checkFeild(login_password);

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = chooseuserpinner.getSelectedItem().toString();
                if (login_email.getText().toString().equals("admin") && login_password.getText().toString().equals("admin") && item.equals("Admin")){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                } else if (login_email.getText().toString().equals("doctor") && login_password.getText().toString().equals("doctor") && item.equals("Doctors")){
                    startActivity(new Intent(LoginActivity.this,DoctorAdminActivity.class));

                } else if (login_email.getText().toString().equals("frontoffice") && login_password.getText().toString().equals("frontoffice") && item.equals("Front - Office")){
                    startActivity(new Intent(LoginActivity.this,FrontOfficeActivity.class));

                }
            }
        });



        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.ChooseUser, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseuserpinner.setAdapter(adapter);

        chooseuserpinner.setOnItemSelectedListener(this);




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

        String choosegender = adapterView.getItemAtPosition(i).toString();


        Toast.makeText(getApplicationContext(), choosegender, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}