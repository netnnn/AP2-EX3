package com.example.ap2_ex3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {User.class, Chat.class, Message.class, Settings.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;

    public abstract UserDao userDao();
    public abstract ChatDao chatDao();
    public abstract MessageDao messageDao();
    public  abstract SettingsDao settingsDao();

    public static synchronized AppDB getDBInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDB.class, "my_database").allowMainThreadQueries().build();
        }
        return instance;
    }
}









