package com.example.clinic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    private List<AppointmentClass> appointmentClassList = new ArrayList<>();
    private DatabaseReference databaseRef;



    public AppointmentAdapter(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;


        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    AppointmentClass appointmentClass = snapshot.getValue(AppointmentClass.class);
                        appointmentClassList.add(appointmentClass);
                    }
                notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {
        AppointmentClass appointmentClass = appointmentClassList.get(position);

        holder.appointment_time.setText(appointmentClass.getAppointmentTime());
        holder.appointment_date.setText(appointmentClass.getAppointmentDate());
        holder.appointment_patient_name.setText(appointmentClass.getAppointmentName());

    }

    @Override
    public int getItemCount() {
        return appointmentClassList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView appointment_time,appointment_date, appointment_patient_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appointment_time = itemView.findViewById(R.id.appointment_time);
            appointment_date = itemView.findViewById(R.id.appointment_date);
            appointment_patient_name = itemView.findViewById(R.id.appointment_patient_name);

        }
    }
}
