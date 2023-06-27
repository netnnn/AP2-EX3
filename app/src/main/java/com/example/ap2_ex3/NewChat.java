package com.example.ap2_ex3;

import com.google.gson.annotations.SerializedName;

public class NewChat {
    @SerializedName("id")
    private int id;
    @SerializedName("user")
    private NewUser user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NewUser getUser() {
        return user;
    }

    public void setUser(NewUser user) {
        this.user = user;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    @SerializedName("lastMessage")
    private Message lastMessage;

    public NewChat(int id, NewUser user, Message lastMessage) {
        this.id = id;
        this.user = user;
        this.lastMessage = lastMessage;
    }
}
