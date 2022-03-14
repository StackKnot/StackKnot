package com.codeup.stackknot.models;

public class TestQuestion {

    private String question;
    private String correctAnswer;
    private String wrongAnswer1;
    private String wronganswer2;
    private String wrongAnswer3;

    public TestQuestion(String question, String correctAnswer, String wrongAnswer1, String wronganswer2, String wrongAnswer3) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wronganswer2 = wronganswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    public TestQuestion() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWronganswer2() {
        return wronganswer2;
    }

    public void setWronganswer2(String wronganswer2) {
        this.wronganswer2 = wronganswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }
}
