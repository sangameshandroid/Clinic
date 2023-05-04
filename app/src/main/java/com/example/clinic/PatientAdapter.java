package com.example.clinic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private List<Patient> patientList = new ArrayList<>();
    private DatabaseReference databaseRef;

    public PatientAdapter(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;


        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("AllPatientFragment", "onDataChange: " + dataSnapshot.toString());

                for (DataSnapshot snapshot : dataSnapshot.getChildren()
                ) {
                    Patient patient = snapshot.getValue(Patient.class);
                    patientList.add(patient);

                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("AllPatientFragment", "onCancelled: " + error.getMessage());


            }
        });
    }


            @NonNull
    @Override
    public PatientAdapter.PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item_list, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.PatientViewHolder holder, int position) {
        Log.d("PatientAdapter", "onBindViewHolder: " + position);



        Patient patient = patientList.get(position);

        if (patient.getFname() != null) {
            holder.patient_firstname.setText("Mr/Ms. " + patient.getFname() + " " + patient.getLname());
        } else {
            Toast.makeText(holder.patient_firstname.getContext(), "Feild is null", Toast.LENGTH_SHORT).show();
        }
            holder.patient_mobile_number.setText("+91-"+  patient.getMobile());
            holder.patient_gender.setText(patient.getGender());
            if (patient.getMartial() != null){
                holder.patient_martial.setText(patient.getMartial());

            } else {
                Toast.makeText(holder.patient_martial.getContext(), "Feild is null", Toast.LENGTH_SHORT).show();

            }

            if (holder.patient_img != null){
            Glide.with(holder.patient_img.getContext()).load(patient.getProfile()).into(holder.patient_img);
            }




    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView patient_firstname, patient_lastname, patient_mobile_number, patient_martial, patient_gender;
        ImageView patient_img, view_icon1;
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);


            patient_mobile_number = itemView.findViewById(R.id.txt_mnumber);
            patient_firstname = itemView.findViewById(R.id.txt_fname );
            patient_martial = itemView.findViewById(R.id.txt_martial);
            patient_gender = itemView.findViewById(R.id.txt_gender);
            patient_img = itemView.findViewById(R.id.patient_imgupload);
            view_icon1 = itemView.findViewById(R.id.view_icon1);

            view_icon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "View is Clicked", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    Fragment fragment = new PatientViewFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = ((FrontOfficeActivity ) view.getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();






                }
            });






        }
    }
}

