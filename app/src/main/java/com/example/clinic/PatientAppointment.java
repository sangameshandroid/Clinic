package com.example.clinic;

public class PatientAppointment {
    private String dateappointment;
    private String timeappointment;
    private String patientname;

    public PatientAppointment(String dateappointment, String timeappointment, String patientname) {
        this.dateappointment = dateappointment;
        this.timeappointment = timeappointment;
        this.patientname = patientname;
    }

    public PatientAppointment() {
    }

    public String getDateappointment() {
        return dateappointment;
    }

    public void setDateappointment(String dateappointment) {
        this.dateappointment = dateappointment;
    }

    public String getTimeappointment() {
        return timeappointment;
    }

    public void setTimeappointment(String timeappointment) {
        this.timeappointment = timeappointment;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }
}
