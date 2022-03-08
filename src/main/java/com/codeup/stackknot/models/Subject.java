package com.codeup.stackknot.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 75, nullable = false)
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Set>sets;

    public Subject() {
    }

    public Subject(String title) {
        this.title = title;
    }

    public Subject(String title, List<Set> sets) {
        this.title = title;
        this.sets = sets;
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

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }
}
