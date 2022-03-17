package com.codeup.stackknot.models;

public class TestQuestion {

    private String question;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;
    private String userChoice;
    private String correctAnswer;
    private Boolean correct;


    public TestQuestion(String question, String answer1, String answer2, String answer3, String answer4) {
        this.question = question;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
    }

    public TestQuestion(String question, String answer1, String answer2, String answer3, String answer4, String userChoice) {
        this.question = question;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
        this.userChoice = userChoice;
    }

    public TestQuestion(String question, String answer1, String answer2, String answer3, String answer4, String userChoice, String correctAnswer) {
        this.question = question;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
        this.userChoice = userChoice;
        this.correctAnswer = correctAnswer;
    }

    public TestQuestion(String question, String answer1, String answer2, String answer3, String answer4, String userChoice, String correctAnswer, Boolean correct) {
        this.question = question;
        Answer1 = answer1;
        Answer2 = answer2;
        Answer3 = answer3;
        Answer4 = answer4;
        this.userChoice = userChoice;
        this.correctAnswer = correctAnswer;
        this.correct = correct;
    }

    public TestQuestion() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public void setAnswer1(String answer1) {
        Answer1 = answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String answer2) {
        Answer2 = answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String answer3) {
        Answer3 = answer3;
    }

    public String getAnswer4() {
        return Answer4;
    }

    public void setAnswer4(String answer4) {
        Answer4 = answer4;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}