package com.example.ap2_ex3;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String username = "";
    private String password = "";
    private String displayName = "";
    private int picture;

    private List<Chat> chatList;

    public User(int id, String username, String password, String displayName, int picture) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.picture = picture;
        this.chatList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPicture() {
        return picture;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public void setPictureBase64(int picture) {
        this.picture = picture;
    }
}
