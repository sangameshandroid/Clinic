package com.example.clinic;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private List<Doctor> doctorlist;
    private Context context;

    public DoctorAdapter( Context context, List<Doctor> doctorlist) {
        this.context = context;
        this.doctorlist = doctorlist;

    }



    @NonNull
    @Override
    public DoctorAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item, parent, false);
        return new  DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.DoctorViewHolder holder, int position) {
        Doctor doctor = doctorlist.get(position);

        holder.doctor_firstname.setText("Dr. " + doctor.getFirstname()  + " " +  doctor.getLastname());
         holder.doctor_specilizastion.setText(doctor.getSpecialization());


    }

    @Override
    public int getItemCount() {
        return doctorlist.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        TextView doctor_firstname, doctor_lastname, doctor_specilizastion;
        ImageView doctorview_icon1;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor_firstname = itemView.findViewById(R.id.doctor_firstname);
            doctor_lastname = itemView.findViewById(R.id.doctor_lastname);
            doctor_specilizastion = itemView.findViewById(R.id.doctor_specilization);
            doctorview_icon1 = itemView.findViewById(R.id.doctorview_icon1);

            doctorview_icon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "View is Clicked", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    Fragment fragment = new DoctorViewFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction ft = ((MainActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();

                }
            });

        }
    }
}
