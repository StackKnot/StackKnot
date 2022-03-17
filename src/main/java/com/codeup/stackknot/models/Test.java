package com.codeup.stackknot.models;

import java.util.List;

public class Test {

    private List<TestQuestion> testQuestions;
    private long setId;
    private double grade;
    private Progression progression;


    public Test() {
    }

    public Test(List<TestQuestion> testQuestions, long setId) {
        this.testQuestions = testQuestions;
        this.setId = setId;
    }

    public Test(List<TestQuestion> testQuestions, long setId, double grade, Progression progression) {
        this.testQuestions = testQuestions;
        this.setId = setId;
        this.grade = grade;
        this.progression = progression;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Progression getProgression() {
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }
}
