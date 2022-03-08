package com.codeup.stackknot.models;

import javax.persistence.*;

@Entity
@Table(name = "user_set_prog")
public class UserSetProg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "set_id")
    private Set set;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "progression_id")
    private Progression progression;

    public UserSetProg() {
    }

    public UserSetProg(User user, Set set, Progression progression) {
        this.user = user;
        this.set = set;
        this.progression = progression;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public Progression getProgression() {
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }
}
