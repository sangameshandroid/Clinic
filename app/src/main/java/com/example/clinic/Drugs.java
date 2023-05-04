package com.example.clinic;

public class Drugs {

    String id;
    String tradeName;
    String genericName;

    public Drugs(String id, String tradeName, String genericName) {
        this.id = id;
        this.tradeName = tradeName;
        this.genericName = genericName;
    }

    public Drugs() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
