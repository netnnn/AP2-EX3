package com.example.ap2_ex3;

import android.net.Uri;

import java.util.List;

public class User {

    private int id;
    private String username = "";
    private String password = "";
    private String displayName = "";
    private Uri pictureBase64 = null;

    private List<Chat> chatList;

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

    public Uri getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(Uri pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }
}
