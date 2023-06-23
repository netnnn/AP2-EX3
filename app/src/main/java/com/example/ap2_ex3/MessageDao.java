//package com.example.ap2_ex3;
//
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface MessageDao {
//
//    @Insert
//    void insert(Message... messages);
//
//    @Update
//    void update(Message... messages);
//
//    @Delete
//    void delete(Message... messages);
//
//    @Query("SELECT * FROM Message")
//    List<User> index();
//
//    @Query("SELECT * FROM Message WHERE id = :id")
//    User get(int id);
//
//}
