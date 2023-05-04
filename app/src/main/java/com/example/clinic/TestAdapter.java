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

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<TestClass> testClassList = new ArrayList<>();
    private DatabaseReference databaseRef;

    public TestAdapter(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TestClass testClass = snapshot.getValue(TestClass.class);
                    testClassList.add(testClass);
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
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        TestClass testClass = testClassList.get(position);

        holder.test_name.setText(testClass.getTestName() );
        holder.test_descriptions.setText(testClass.getTestDescriptions());

    }

    @Override
    public int getItemCount() {
        return testClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView test_name, test_descriptions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            test_name = itemView.findViewById(R.id.test_name);
            test_descriptions = itemView.findViewById(R.id.test_discriptions);
        }
    }
}
