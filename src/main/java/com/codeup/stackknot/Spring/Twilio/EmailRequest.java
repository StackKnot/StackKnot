package com.codeup.stackknot.Spring.Twilio;

public class EmailRequest {

    private String from;
    private String subject;
    private String body;

    public String getFrom() {
        return from;
    }
    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public EmailRequest(String from, String subject, String body) {
        super();
        this.from = from;
        this.subject = subject;
        this.body =body;
    }
    public EmailRequest(){}

    public void setFrom(String from) {
        this.from = from;
    }
}
