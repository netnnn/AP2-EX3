package com.example.ap2_ex3;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Settings {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "server url")
    private String serverURL;
    @ColumnInfo(name = "dark mode")
    private boolean darkMode;

    public Settings(String serverURL, boolean darkMode) {
        this.serverURL = serverURL;
        this.darkMode = darkMode;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
