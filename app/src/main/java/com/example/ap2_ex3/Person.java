package com.example.ap2_ex3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Person {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "first name")
    public String firstName;

    @ColumnInfo(name = "last name")
    public String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
