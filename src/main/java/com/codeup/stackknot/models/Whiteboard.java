package com.codeup.stackknot.models;

import javax.persistence.*;
import com.cloudinary.StoredFile;
@Entity
@Table(name = "whiteboards")
public class Whiteboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String jsURL;
    @Column(nullable = false)
    private String javaURL;

    public Whiteboard() {
    }

    public Whiteboard(String question, String jsURL, String javaURL) {
        this.question = question;
        this.jsURL = jsURL;
        this.javaURL = javaURL;
    }

    public Whiteboard(long id, String question, String jsURL, String javaURL) {
        this.id = id;
        this.question = question;
        this.jsURL = jsURL;
        this.javaURL = javaURL;
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

    public String getJsURL() {
        return jsURL;
    }

    public void setJsURL(String jsURL) {
        this.jsURL = jsURL;
    }

    public String getJavaURL() {
        return javaURL;
    }

    public void setJavaURL(String javaURL) {
        this.javaURL = javaURL;
    }

    public StoredFile getUpload() {
        StoredFile file = new StoredFile();
        file.setPreloadedFile(jsURL);
        file.setPreloadedFile(javaURL);
        return file;
    }


}
