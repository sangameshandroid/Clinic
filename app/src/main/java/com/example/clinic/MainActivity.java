package com.example.clinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ImageView hamburger;
    NavigationView nav_view;
    private ExpandableListView elv;
    private NavListAdapter adapter;
    private List<navitems> navitemsList;
    Fragment fragment = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        navitemsList = generateNavItemList();
        adapter = new NavListAdapter(this, navitemsList);
        elv.setAdapter(adapter);
        elv.setGroupIndicator(null);
        fragment = new DashBoardFragment() ;
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
                        case 3:
                            fragment = new AppointmentFragment();
                            break;
                        case 5:
                            fragment = new AddDrugFragment();
                            break;
                        case 7:
                            fragment = new CalenderFragment();
                            break;
                        case 8:
                            fragment = new ReportFragment();
                            break;
                        case 9:
                            fragment = new RegisterAllAdminFragment();
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


                  /*  case 2:
                        switch (childPosition){
                            case 0:
                                fragment = new AllDoctorFragment();
                                break;
                            case 1:
                                fragment = new DoctorsProfileFragment();
                                break;
                        }
                        break;*/

                    case 4:
                        switch (childPosition){
                            case 0:
                                fragment = new AddTestFragment();
                                break;
                            case 1:
                                fragment = new AllTestFragment();
                                break;
                        }
                        break;
                    case 2:
                        switch (childPosition){

                            case 0:
                                fragment = new AllDoctorsListFragment();
                                break;
                            case 1:
                                fragment = new DoctorsProfilesFragment();
                                break;
                        }
                        break;


                /*    case 6:
                        switch (childPosition){
                            case 0:
                                fragment = new AddTestFragment();
                                break;
                            case 1:
                                fragment = new AllTestFragment();
                                break;
                        }
                        break;*/

                 /*   case 9:
                        switch (childPosition){
                            case 0:
                                fragment = new CreateInvoiceFragment();
                                break;
                            case 1:
                                fragment = new BillingListFragment();
                                break;
                        }
                        break;*/
                    case 10:
                        switch (childPosition){
                            case 0:
                                fragment = new PrescriptionSettingFragment();
                                break;
                            case 1:
                                fragment = new DoctorSettingFragment();
                                break;
                        }
                        break;
                }
                if(fragment!=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });








    }

    private List<navitems>  generateNavItemList(){
        List<navitems> navitem = new ArrayList<>();

        navitem.add(new navitems("DashBoard", R.drawable.dashboard1,null));
        navitem.add(new navitems("Register", R.drawable.dashboard1, null));

        List<SubNavItem> doc = new ArrayList<>();
        doc.add(new SubNavItem("All Doctors"));
        doc.add(new SubNavItem("Doctor's Profile"));
        navitem.add(new navitems("Doctors", R.drawable.doctors, doc));

      /*  List<SubNavItem> patient = new ArrayList<>();
        patient.add(new SubNavItem("New Patient"));
        patient.add(new SubNavItem("All Patients"));
        navitem.add(new navitems("Patients", R.drawable.patient1, patient));*/

       // navitem.add(new navitems("Appointment", R.drawable.schedule, null));

      /*  List<SubNavItem> presc = new ArrayList<>();
        presc.add(new SubNavItem("New Prescription"));
        presc.add(new SubNavItem("All Prescriptions"));
        navitem.add(new navitems("Prescription", R.drawable.pres, presc));*/

        navitem.add(new navitems("Add Drugs", R.drawable.drugs1, null));

       List<SubNavItem> test = new ArrayList<>();
        test.add(new SubNavItem("Add Test"));
        test.add(new SubNavItem("All Tests"));
        navitem.add(new navitems("Test", R.drawable.tests, test));

       // navitem.add(new navitems("Calender", R.drawable.calendar1, null));
        navitem.add(new navitems("Reports", R.drawable.reportss, null));

      /*  List<SubNavItem> bill = new ArrayList<>();
        bill.add(new SubNavItem("Create Invoice"));
        bill.add(new SubNavItem("Billing List"));
        navitem.add(new navitems("Billing", R.drawable.invoice, bill));*/

        List<SubNavItem> set = new ArrayList<>();
        set.add(new SubNavItem("Prescription Settings"));
        set.add(new SubNavItem("Doctor Settings"));
        navitem.add(new navitems("Settings", R.drawable.settings1, set));

        return navitem;

    }


}