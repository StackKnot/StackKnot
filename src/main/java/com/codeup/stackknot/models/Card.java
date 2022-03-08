package com.codeup.stackknot.models;

import javax.persistence.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "set_id")
    private Set set;


    public Card() {
    }

    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Card(long id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Card(long id, String question, String answer, Set set) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.set = set;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }
}
