package com.example.ap2_ex3;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
//    @ColumnInfo(name = "drawable picture")
//    public Drawable dPicture;
    @ColumnInfo(name = "chat list")
    private List<Chat> chatList;

//    @ColumnInfo(name = "bitmap")
//    private Bitmap bitmap;

        @ColumnInfo(name = "base64")
        private String base64;

    public User(String username, String password, String displayName, int picture) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.picture = picture;
        this.chatList = new ArrayList<>();
    }

    @Ignore

    public User(String username, String password, String displayName, String base64) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.base64 = base64;
        this.chatList = new ArrayList<>();
    }

    public void setPicture(int picture) {
        this.picture = picture;
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

//    public Drawable getdPicture() {
//        return dPicture;
//    }

//    public Bitmap getBitmap() {
//        return bitmap;
//    }
//
//    public void setBitmap(Bitmap byteArray) {
//        this.bitmap = byteArray;
//    }


    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Chat findChatWith(String friendName){

        for (Chat chat: chatList) {
            if (chat.getUserOneName().equals(friendName) || chat.getUserTwoName().equals(friendName)){
                return chat;
            }

        }
        return null;
    }
}
