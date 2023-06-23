package com.example.ap2_ex3;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


//@Database(entities = {User.class, Chat.class, Message.class}, version = 1)
@Database(entities = {User.class, Chat.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDB extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract ChatDao chatDao();
//    public abstract MessageDao messageDao();
}









