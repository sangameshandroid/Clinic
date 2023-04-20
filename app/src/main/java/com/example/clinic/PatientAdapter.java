package com.example.clinic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {
    private List<Patient> mPatients;

    public PatientAdapter(List<Patient> patients) {
        mPatients = patients;
    }


    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item_list, parent, false);
        return new PatientViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.PatientViewHolder holder, int position) {
        Patient patient = mPatients.get(position);
        holder.patientfirstname.setText(patient.getPatientfirstame());
        holder.patientlastname.setText(patient.getPatientlastname());
        holder.patient_mobile_number.setText(patient.getPatientmobile());
        holder.patient_martial.setText(patient.getPatientmartialstatus());
        holder.patient_gender.setText(patient.getPatientgender());
        holder.patient_img.setImageResource(patient.getPatientimg());

    }

    @Override
    public int getItemCount() {
        return mPatients.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {

        ImageView patient_img;
        TextView patientfirstname, patientlastname, patient_mobile_number, patient_martial, patient_gender;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);

            patient_img = itemView.findViewById(R.id.patient_img);
            patientfirstname = itemView.findViewById(R.id.patient_firstname);
            patientlastname = itemView.findViewById(R.id.patient_lastname);
            patient_mobile_number = itemView.findViewById(R.id.patient_mobile_number);
            patient_martial = itemView.findViewById(R.id.patient_martial);
            patient_gender = itemView.findViewById(R.id.patient_gender);





        }
    }
}
