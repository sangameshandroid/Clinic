package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class FrontOfficeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView hamburger;
    NavigationView nav_view;
    private ExpandableListView elv;
    private FrontOfficeListAdapter adapter;
    private List<FrontOfficeNavItem> frontnavitemsList;
    Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_office);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.Drawerlayout);
        nav_view = findViewById(R.id.nav_view);
        hamburger = findViewById(R.id.hamburger);
        elv = findViewById(R.id.expandableListView);

        hamburger.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawerLayout. openDrawer(GravityCompat.START);
            }
        });
        frontnavitemsList = generateFrontOfficeNavItemList();
        adapter = new FrontOfficeListAdapter(this, frontnavitemsList);
        elv.setAdapter(adapter);
        elv.setGroupIndicator(null);
        fragment = new Patientregistration();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        elv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    if (i != groupPosition && elv.isGroupExpanded(i)) {
                        elv.collapseGroup(i);
                    }
                }
            }

        });
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (adapter.getChildrenCount(groupPosition) > 0) {
                    if (elv.isGroupExpanded(groupPosition)) {
                        elv.collapseGroup(groupPosition);
                    } else {
                        elv.expandGroup(groupPosition);
                    }
                    return true;
                }else{

                    switch (groupPosition){

                        case 0:
                            fragment = new Patientregistration();
                            break;
                        case 3:
                            fragment = new AppointmentFragment();
                            break;
                        /*case 5:
                            fragment = new AddDrugFragment();
                            break;*/
                        case 7:
                            fragment = new CalenderFragment();
                            break;
                        case 8:
                            fragment = new ReportFragment();
                            break;
                       /* case 9:
                            fragment = new RegisterAllAdminFragment();
                            break;*/

                    }
                    if(fragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;

                }


            }
        });

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                switch (groupPosition){
                    case 1:
                        switch (childPosition){
                            case 0:
                                fragment = new NewPatientFragment();
                                break;
                            case 1:
                                fragment = new AllPatientFragment();
                                break;
                        }
                        break;

                    case 2:
                        switch (childPosition){
                            case 0:
                                fragment = new CreateInvoiceFragment();
                                break;
                            case 1:
                                fragment = new BillingListFragment();
                                break;
                        }
                        break;

                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false ;
            }
        });









    }

    private List<FrontOfficeNavItem> generateFrontOfficeNavItemList() {
        List<FrontOfficeNavItem> frontnavitem = new ArrayList<>();

        //frontnavitem.add(new FrontOfficeNavItem("DashBoard", R.drawable.dashboard1,null));
        frontnavitem.add(new FrontOfficeNavItem("Register", R.drawable.dashboard1, null));

        List<FrontOfficeSubNavItem> patient = new ArrayList<>();
        patient.add(new FrontOfficeSubNavItem("New Patient"));
        patient.add(new FrontOfficeSubNavItem("All Patient"));
        frontnavitem.add(new FrontOfficeNavItem("Patient", R.drawable.patient1, patient));

        List<FrontOfficeSubNavItem> bill = new ArrayList<>();
        bill.add(new FrontOfficeSubNavItem("Create Invoice"));
        bill.add(new FrontOfficeSubNavItem("Billing List"));
        frontnavitem.add(new FrontOfficeNavItem("Billing", R.drawable.invoice, bill));

        frontnavitem.add(new FrontOfficeNavItem("Calender", R.drawable.calendar1, null));
        frontnavitem.add(new FrontOfficeNavItem("Appointment", R.drawable.calendar1, null));

        return frontnavitem;

    }
}