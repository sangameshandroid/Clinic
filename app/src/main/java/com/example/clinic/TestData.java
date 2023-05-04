package com.example.clinic;

public class TestData {
    String testdataName;
    String testdataDescriptions;

    public TestData(String testdataName, String testdataDescriptions) {
        this.testdataName = testdataName;
        this.testdataDescriptions = testdataDescriptions;
    }

    public TestData() {
    }

    public String getTestdataName() {
        return testdataName;
    }

    public void setTestdataName(String testdataName) {
        this.testdataName = testdataName;
    }

    public String getTestdataDescriptions() {
        return testdataDescriptions;
    }

    public void setTestdataDescriptions(String testdataDescriptions) {
        this.testdataDescriptions = testdataDescriptions;
    }
}
