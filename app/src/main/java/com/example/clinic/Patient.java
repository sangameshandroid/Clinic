package com.example.clinic;

public class Patient {
    String fname;
    String lname;
    String mobile;
    String gender;
    String martial;
    String profile;

    public Patient(String fname, String lname, String mobile, String gender, String martial, String profile) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.gender = gender;
        this.martial = martial;
        this.profile = profile;
    }

    public Patient() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMartial() {
        return martial;
    }

    public void setMartial(String martial) {
        this.martial = martial;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
