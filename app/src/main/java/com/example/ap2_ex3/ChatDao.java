package com.example.ap2_ex3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ChatDao {

    @Insert
    void insert(Chat... chats);

    @Update
    void update(Chat... chats);

    @Delete
    void delete(Chat... chats);

    @Query("SELECT * FROM Chat")
    List<User> index();

    @Query("SELECT * FROM Chat WHERE id = :id")
    User get(int id);

}
