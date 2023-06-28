package com.example.ap2_ex3.Messages;

import com.example.ap2_ex3.Users.GetUserRes;

public class MsgData {

    int id;
    String created, content;
    GetUserRes sender;

    public MsgData(int id, String created, String content, GetUserRes sender) {
        this.id = id;
        this.created = created;
        this.content = content;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GetUserRes getSender() {
        return sender;
    }

    public void setSender(GetUserRes sender) {
        this.sender = sender;
    }
}
