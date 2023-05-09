package com.example.clinic;

public class PrescriptionsClass {
    String patientname;
    String druglist;
    String ml;
    String dose;
    String duration;
    String comment;
    String testlist;
    String testdiscriptions;

    public PrescriptionsClass(String patientname, String druglist, String ml, String dose, String duration, String comment, String testlist, String testdiscriptions) {
        this.patientname = patientname;
        this.druglist = druglist;
        this.ml = ml;
        this.dose = dose;
        this.duration = duration;
        this.comment = comment;
        this.testlist = testlist;
        this.testdiscriptions = testdiscriptions;
    }

    public PrescriptionsClass() {
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }



    public String getDruglist() {
        return druglist;
    }

    public void setDruglist(String druglist) {
        this.druglist = druglist;
    }

    public String getMl() {
        return ml;
    }

    public void setMl(String ml) {
        this.ml = ml;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTestlist() {
        return testlist;
    }

    public void setTestlist(String testlist) {
        this.testlist = testlist;
    }

    public String getTestdiscriptions() {
        return testdiscriptions;
    }

    public void setTestdiscriptions(String testdiscriptions) {
        this.testdiscriptions = testdiscriptions;
    }
}
