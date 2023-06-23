package com.example.ap2_ex3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class personDataBase extends RoomDatabase {
    public abstract PersonDao personDao();
}
