package com.example.ap2_ex3.Chats;

import com.example.ap2_ex3.Messages.MsgData;
import com.example.ap2_ex3.Users.GetUserRes;

import java.util.List;

public class ChatData {
    int id;
    List<GetUserRes> users;
    List<MsgData> messages;

    public ChatData(int id, List<GetUserRes> users, List<MsgData> messages) {
        this.id = id;
        this.users = users;
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GetUserRes> getUsers() {
        return users;
    }

    public void setUsers(List<GetUserRes> users) {
        this.users = users;
    }

    public List<MsgData> getMessages() {
        return messages;
    }

    public void setMessages(List<MsgData> messages) {
        this.messages = messages;
    }
}


