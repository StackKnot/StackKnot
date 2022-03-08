package com.codeup.stackknot.models;

import javax.persistence.*;

@Entity
@Table(name = "progression")
public class Progression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String status;

    public Progression() {
    }

    public Progression(String status) {
        this.status = status;
    }

    public Progression(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
