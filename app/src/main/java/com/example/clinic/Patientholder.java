package com.example.clinic;

import android.widget.Spinner;

public class Patientholder {
    String fname, lname, email, mobile, date, weight, height, address, history, period,profile,
    gender, martialstatus, bloodgroup, knowndiseases, familyhistory, diseases;

    public Patientholder(String fname, String lname, String email, String mobile, String date, String weight, String height, String address, String history, String period,String profile, String gender, String martialstatus, String bloodgroup, String knowndiseases, String familyhistory, String diseases) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.address = address;
        this.history = history;
        this.period = period;
        this.profile = profile;
        this.gender = gender;
        this.martialstatus = martialstatus;
        this.bloodgroup = bloodgroup;
        this.knowndiseases = knowndiseases;
        this.familyhistory = familyhistory;
        this.diseases = diseases;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profileimg) {
        this.profile = profile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMartialstatus() {
        return martialstatus;
    }

    public void setMartialstatus(String martialstatus) {
        this.martialstatus = martialstatus;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getKnowndiseases() {
        return knowndiseases;
    }

    public void setKnowndiseases(String knowndiseases) {
        this.knowndiseases = knowndiseases;
    }

    public String getFamilyhistory() {
        return familyhistory;
    }

    public void setFamilyhistory(String familyhistory) {
        this.familyhistory = familyhistory;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }
}
