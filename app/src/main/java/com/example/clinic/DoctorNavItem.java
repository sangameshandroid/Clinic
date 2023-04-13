package com.example.clinic;

import java.util.List;

public class DoctorNavItem {

    private String title;
    private int icon;
    private List<DoctorSubItem> doctorsubItems;

    public DoctorNavItem(String title, int icon, List<DoctorSubItem> doctorsubItems) {
        this.title = title;
        this.icon = icon;
        this.doctorsubItems = doctorsubItems;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<DoctorSubItem> getDoctorsubItems() {
        return doctorsubItems;
    }

    public void setDoctorsubItems(List<DoctorSubItem> doctorsubItems) {
        this.doctorsubItems = doctorsubItems;
    }
}
