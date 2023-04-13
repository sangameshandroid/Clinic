package com.example.clinic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdminActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView hamburger;
    NavigationView nav_view;
    private ExpandableListView elv;
    private DoctorNavLIstAdapter adapter;
    private List<DoctorNavItem> doctornavitemsList;
    Fragment fragment = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_admin);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.Drawerlayout);
        nav_view = findViewById(R.id.nav_view);
        hamburger = findViewById(R.id.hamburger);
        elv = findViewById(R.id.expandableListView);

        hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout. openDrawer(GravityCompat.START);

            }
        });
        doctornavitemsList = generateDoctorNavItem();
        adapter = new DoctorNavLIstAdapter(this, doctornavitemsList);
        elv.setAdapter(adapter);
        elv.setGroupIndicator(null);
        fragment = new DashBoardFragment();
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
                            fragment = new DashBoardFragment();
                            break;
                        case 1:
                            fragment = new NewPrescriptionFragment();
                            break;
                        case 2:
                            fragment = new AllPrescriptionFragment();
                            break;
                      /*  case 3:
                            fragment = new AppointmentFragment();
                            break;*/
/*                            fragment = new AddDrugFragment();
                            break;*/
                        case 7:
                            fragment = new CalenderFragment();
                            break;
                        case 8:
                            fragment = new ReportFragment();
                            break;
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
                                fragment = new NewPrescriptionFragment();
                                break;
                            case 1:
                                fragment = new AllPrescriptionFragment();
                                break;

                        }
                        break;
                 /*   case 2:
                        switch (childPosition){
                            case 0:
                                fragment = new NewPatientFragment();
                                break;
                            case 1:
                                fragment = new AllPatientFragment();
                                break;
                        }
                        break;*/

                  /*  case 4:
                        switch (childPosition){
                            case 0:
                                fragment = new NewPrescriptionFragment();
                                break;
                            case 1:
                                fragment = new AllPrescriptionFragment();
                                break;
                        }
                        break;

                    case 6:
                        switch (childPosition){
                            case 0:
                                fragment = new AddTestFragment();
                                break;
                            case 1:
                                fragment = new AllTestFragment();
                                break;
                        }
                        break;

                    case 9:
                        switch (childPosition){
                            case 0:
                                fragment = new CreateInvoiceFragment();
                                break;
                            case 1:
                                fragment = new BillingListFragment();
                                break;
                        }
                        break;
                    case 10:
                        switch (childPosition){
                            case 0:
                                fragment = new PrescriptionSettingFragment();
                                break;
                            case 1:
                                fragment = new DoctorSettingFragment();
                                break;
                        }
                        break;*/
                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    private List<DoctorNavItem> generateDoctorNavItem() {
        List<DoctorNavItem> doctornavitem = new ArrayList<>();

        doctornavitem.add(new DoctorNavItem("DashBoard", R.drawable.dashboard1,null));
       // doctornavitem.add(new DoctorNavItem("Register", R.drawable.dashboard1, null));
     /*   List<DoctorSubItem> doc = new ArrayList<>();
        doc.add(new DoctorSubItem("Add Doctor"));
        doc.add(new DoctorSubItem("All Doctors"));
        doc.add(new DoctorSubItem("Doctor's Profile"));
        doctornavitem.add(new DoctorNavItem("Doctors", R.drawable.doctors, doc));*/


        /*  List<SubNavItem> patient = new ArrayList<>();
        patient.add(new SubNavItem("New Patient"));
        patient.add(new SubNavItem("All Patients"));
        navitem.add(new navitems("Patients", R.drawable.patient1, patient));*/

        // navitem.add(new navitems("Appointment", R.drawable.schedule, null));
        List<DoctorSubItem> presc = new ArrayList<>();
        presc.add(new DoctorSubItem("New Prescription"));
        presc.add(new DoctorSubItem("All Prescriptions"));
        doctornavitem.add(new DoctorNavItem("Prescription", R.drawable.pres, presc));

       // doctornavitem.add(new DoctorNavItem("Add Drugs", R.drawable.drugs1, null));

     /*   List<DoctorSubItem> test = new ArrayList<>();
        test.add(new DoctorSubItem("Add Test"));
        test.add(new DoctorSubItem("All Tests"));
        doctornavitem.add(new DoctorNavItem("Test", R.drawable.tests, test));*/

        doctornavitem.add(new DoctorNavItem("Calender", R.drawable.calendar1, null));
        doctornavitem.add(new DoctorNavItem("Reports", R.drawable.reportss, null));

      /*  List<SubNavItem> bill = new ArrayList<>();
        bill.add(new SubNavItem("Create Invoice"));
        bill.add(new SubNavItem("Billing List"));
        navitem.add(new navitems("Billing", R.drawable.invoice, bill));*/

      /*  List<DoctorSubItem> set = new ArrayList<>();
        set.add(new DoctorSubItem("Prescription Settings"));
        set.add(new DoctorSubItem("Doctor Settings"));
        doctornavitem.add(new DoctorNavItem("Settings", R.drawable.settings1, set));*/

        return doctornavitem ;


    }
}