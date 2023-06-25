package com.example.ap2_ex3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class Converters {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<Chat> fromStringToChatList(String value) {
//        return gson.fromJson(value, List<Chat>.class );
        Type listType = new TypeToken<List<Chat>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromChatListToString(List<Chat> myObject) {
        return gson.toJson(myObject);
    }

//    @TypeConverter
//    public static Drawable fromStringToDrawable(String value) {
//        return gson.fromJson(value, Drawable.class );
//    }

//    @TypeConverter
//    public static String fromDrawableToString(Drawable myObject) {
//        return gson.toJson(myObject);
//    }

    @TypeConverter
    public static List<Message> fromStringToMsgList(String value) {
        Type listType = new TypeToken<List<Message>>() {}.getType();
        return gson.fromJson(value, listType);
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

    @TypeConverter
    public static String fromBitmapToString(Bitmap myObject) {
        return gson.toJson(myObject);
    }

    @TypeConverter
    public static Bitmap fromStringToBitmap(String value) {
        return gson.fromJson(value, Bitmap.class );
    }







//    public class DrawableTypeConverter {
//        private DrawableTypeConverter instance;
//
//
//        public DrawableTypeConverter getInstance() {
//            if (instance == null) {
//                instance = new DrawableTypeConverter();
//            }
//            return instance;
//        }
//    }
//
//        @TypeConverter
//        public static byte[] fromDrawable(Drawable drawable) {
//            if (drawable == null){
//                return null;
//            }
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            Bitmap bitmap = drawableToBitmap(drawable);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//            return outputStream.toByteArray();
//        }
//
//        @TypeConverter
//        public static Drawable toDrawable(byte[] byteArray) {
//            if (byteArray == null) {
//                return null;
//            }
//            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//            return new BitmapDrawable(Resources.getSystem(), bitmap);
//        }
//
//        private static Bitmap drawableToBitmap(Drawable drawable) {
//            if (drawable instanceof BitmapDrawable) {
//                return ((BitmapDrawable) drawable).getBitmap();
//            }
//
//            if (drawable == null) {
//                return null;
//            }
//
//            int width = drawable.getIntrinsicWidth();
//            int height = drawable.getIntrinsicHeight();
//
//            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//            drawable.draw(canvas);
//
//            return bitmap;
//        }


}