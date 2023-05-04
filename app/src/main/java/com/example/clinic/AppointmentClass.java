package com.example.clinic;

public class AppointmentClass {
    String appointmentTime;
    String appointmentDate;
    String appointmentName;
    String appointmentaddress;
    String appointmentmobile;
    String appointmentStatus;

    public AppointmentClass(String appointmentTime, String appointmentDate, String appointmentName, String appointmentaddress, String appointmentmobile, String appointmentStatus) {
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.appointmentName = appointmentName;
        this.appointmentaddress = appointmentaddress;
        this.appointmentmobile = appointmentmobile;
        this.appointmentStatus = appointmentStatus;
    }

    public AppointmentClass() {
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentName() {
        return appointmentName;
    }

    public void setAppointmentName(String appointmentName) {
        this.appointmentName = appointmentName;
    }

    public String getAppointmentaddress() {
        return appointmentaddress;
    }

    public void setAppointmentaddress(String appointmentaddress) {
        this.appointmentaddress = appointmentaddress;
    }

    public String getAppointmentmobile() {
        return appointmentmobile;
    }

    public void setAppointmentmobile(String appointmentmobile) {
        this.appointmentmobile = appointmentmobile;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }
}
