package com.example.clinic;

public class Patient {
    String patientfirstame;
    String patientlastname;
    String patientmobile;
    String patientmartialstatus;
    String patientgender;
    int patientimg;



    public Patient(String patientfirstame, String patientlastname, String patientmobile, String patientmartialstatus, String patientgender, int patientimg) {
        this.patientfirstame = patientfirstame;
        this.patientlastname = patientlastname;
        this.patientmobile = patientmobile;
        this.patientmartialstatus = patientmartialstatus;
        this.patientgender = patientgender;
        this.patientimg = patientimg;
    }

    public String getPatientfirstame() {
        return patientfirstame;
    }

    public void setPatientfirstame(String patientfirstame) {
        this.patientfirstame = patientfirstame;
    }

    public String getPatientlastname() {
        return patientlastname;
    }

    public void setPatientlastname(String patientlastname) {
        this.patientlastname = patientlastname;
    }

    public String getPatientmobile() {
        return patientmobile;
    }

    public void setPatientmobile(String patientmobile) {
        this.patientmobile = patientmobile;
    }

    public String getPatientmartialstatus() {
        return patientmartialstatus;
    }

    public void setPatientmartialstatus(String patientmartialstatus) {
        this.patientmartialstatus = patientmartialstatus;
    }

    public int getPatientimg() {
        return patientimg;
    }

    public void setPatientimg(int patientimg) {
        this.patientimg = patientimg;
    }
    public String getPatientgender() {
        return patientgender;
    }
    public void setPatientgender(String patientgender) {
        this.patientgender = patientgender;
    }


}
