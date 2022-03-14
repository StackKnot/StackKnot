package com.codeup.stackknot.models;

import java.util.List;

public class Test {

    private List<TestQuestion> testQuestions;

    public Test(List<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }

    public List<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(List<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }


}
