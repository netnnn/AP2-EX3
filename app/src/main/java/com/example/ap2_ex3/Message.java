package com.example.ap2_ex3;

import java.util.Date;

public class Message {

    private int id;
    private Date date;
    private String content;
    private User sender;

    public Message(int id, Date date, String content, User sender) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
