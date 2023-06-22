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

    @Query("SELECT * FROM User")
    List<User> index();

    @Query("SELECT * FROM User WHERE username = :username")
    User get(String username);

}
