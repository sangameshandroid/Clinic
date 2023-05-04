package com.example.clinic;

public class Doctor {
    private String uid;
    private String firstname;
    private String lastname;
    private String specialization;

    public Doctor(String firstname, String lastname, String specialization, String uid) {
        this.uid = uid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
    }

    public Doctor() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
