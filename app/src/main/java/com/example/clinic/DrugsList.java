package com.example.clinic;

public class DrugsList {
    String tradeName;
    String genericName;
    String drugsNote;
    String drugsID;

    public DrugsList(String tradeName, String genericName, String drugsNote, String drugsID) {
        this.tradeName = tradeName;
        this.genericName = genericName;
        this.drugsNote = drugsNote;
        this.drugsID = drugsID;
    }

    public DrugsList() {
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDrugsNote() {
        return drugsNote;
    }

    public void setDrugsNote(String drugsNote) {
        this.drugsNote = drugsNote;
    }

    public String getDrugsID() {
        return drugsID;
    }

    public void setDrugsID(String drugsID) {
        this.drugsID = drugsID;
    }
}
