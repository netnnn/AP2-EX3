package com.example.ap2_ex3;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "username")
    private String username = "";
    @ColumnInfo(name = "password")
    private String password = "";
    @ColumnInfo(name = "display name")
    private String displayName = "";
    @ColumnInfo(name = "picture")
    private int picture = 0;
    @ColumnInfo(name = "drawable picture")
    public Drawable dPicture;
    @ColumnInfo(name = "chat list")
    private List<Chat> chatList;

    public User(String username, String password, String displayName, int picture) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.picture = picture;
        this.chatList = new ArrayList<>();
    }

    public User(String username, String password, String displayName, Drawable picture) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.dPicture = picture;
        this.chatList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Drawable getdPicture() {
        return dPicture;
    }
}
