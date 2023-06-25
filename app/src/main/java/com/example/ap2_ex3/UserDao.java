package com.example.ap2_ex3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT * FROM user")
    List<User> index();

    @Query("DELETE FROM user WHERE username != 'xxx' ")
    void deleteAll();

    @Query("SELECT * FROM user WHERE username = :username")
    User get(String username);

    @Query("SELECT `chat list` FROM user WHERE username = :username")
    List<Chat> getChatList(String username);

}
