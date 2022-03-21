package com.codeup.stackknot.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sets")
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int likes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "set")
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set() {
        this.likes = 0;
    }

    public Set(String title, String description) {
        this.title = title;
        this.description = description;
        this.likes = 0;
    }

    public Set(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.likes = 0;
    }

    public Set(String title, String description, User user, Subject subject) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.subject = subject;
        this.likes = 0;
    }



    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
