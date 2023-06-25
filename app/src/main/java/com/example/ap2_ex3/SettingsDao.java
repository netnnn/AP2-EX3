package com.example.ap2_ex3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SettingsDao {


    @Insert
    void insert(Settings... settings);

    @Update
    void update(Settings... settings);

    @Delete
    void delete(Settings... settings);

    @Query("SELECT * FROM Settings LIMIT 1")
    Settings index();

    @Query("SELECT `server url` FROM Settings LIMIT 1")
    String getURL();

    @Query("SELECT `dark mode` FROM Settings LIMIT 1")
    boolean getDarkMode();

}
