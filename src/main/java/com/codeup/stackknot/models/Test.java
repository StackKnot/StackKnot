package com.codeup.stackknot.models;

import java.util.List;

public class Test {

    private List<TestQuestion> testQuestions;
    private long setId;


    public Test() {
    }

    public Test(List<TestQuestion> testQuestions, long setId) {
        this.testQuestions = testQuestions;
        this.setId = setId;
    }

    public Test(List<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }

    public List<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(List<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }

    public long getSetId() {
        return setId;
    }

    public void setSetId(long setId) {
        this.setId = setId;
    }



}
