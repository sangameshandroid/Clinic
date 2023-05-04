package com.example.clinic;

public class TestClass {
    String testName;
    String testDescriptions;

    public TestClass(String testName, String testDescriptions) {
        this.testName = testName;
        this.testDescriptions = testDescriptions;
    }

    public TestClass() {
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescriptions() {
        return testDescriptions;
    }

    public void setTestDescriptions(String testDescriptions) {
        this.testDescriptions = testDescriptions;
    }
}
