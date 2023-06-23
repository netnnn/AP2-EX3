package com.example.ap2_ex3;

import android.graphics.drawable.Drawable;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

public class Converters {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Chat> fromStringToChatList(String value) {
        return gson.fromJson(value, List.class );
    }

    @TypeConverter
    public static String fromChatListToString(List<Chat> myObject) {
        return gson.toJson(myObject);
    }

    @TypeConverter
    public static Drawable fromStringToDrawable(String value) {
        return gson.fromJson(value, Drawable.class );
    }

    @TypeConverter
    public static String fromDrawableToString(Drawable myObject) {
        return gson.toJson(myObject);
    }

    @TypeConverter
    public static List<Message> fromStringToMsgList(String value) {
        return gson.fromJson(value, List.class );
    }

    @TypeConverter
    public static String fromMsgListToString(List<Message> myObject) {
        return gson.toJson(myObject);
    }



    @TypeConverter
    public static User fromStringToUser(String value) {
        return gson.fromJson(value, User.class );
    }

    @TypeConverter
    public static String fromUsertoString(User myObject) {
        return gson.toJson(myObject);
    }

    @TypeConverter
    public static Date fromStringToDate(String value) {
        return gson.fromJson(value, Date.class );
    }

    @TypeConverter
    public static String fromDateToString(Date myObject) {
        return gson.toJson(myObject);
    }

    @TypeConverter
    public static Chat fromStringToChat(String value) {
        return gson.fromJson(value, Chat.class );
    }

    @TypeConverter
    public static String fromChatToString(Chat myObject) {
        return gson.toJson(myObject);
    }

    @TypeConverter
    public static Message fromStringToMessage(String value) {
        return gson.fromJson(value, Message.class );
    }

    @TypeConverter
    public static String fromMessageToString(Message myObject) {
        return gson.toJson(myObject);
    }



}