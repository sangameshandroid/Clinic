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

import java.util.ArrayList;
import java.util.List;

public class DrugsAdapter extends RecyclerView.Adapter<DrugsAdapter.ViewHolder>{

    private List<Drugs> listDrug = new ArrayList<>();
    private DatabaseReference databaseRef;

    public DrugsAdapter(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Drugs drugs = snapshot.getValue(Drugs.class);
                    listDrug.add(drugs);
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
    public DrugsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drugs_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugsAdapter.ViewHolder holder, int position) {

        Drugs drugs = listDrug.get(position);

        holder.drugs_id.setText(drugs.getId());
        holder.drugs_trade_name.setText(drugs.getTradeName());
        holder.drugs_generic_name.setText(drugs.getGenericName());

    }

    @Override
    public int getItemCount() {
        return listDrug.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView drugs_id, drugs_trade_name, drugs_generic_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            drugs_id = itemView.findViewById(R.id.drugs_id);
            drugs_trade_name = itemView.findViewById(R.id.drugs_trade_name);
            drugs_generic_name = itemView.findViewById(R.id.drugs_generic_name);

        }
    }
}
