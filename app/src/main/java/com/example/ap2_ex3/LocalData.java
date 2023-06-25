package com.example.ap2_ex3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalData {
    public static List<User> users = new ArrayList<>();
    private static boolean initialized = false;

    public static String serverUrl = "http://10.0.2.2:5000";

    public static User getUserByName(String username) {
        for (User user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

//    public static Chat getChatByName(String myUsername, String friend) {
//        if (getUserByName(myUsername).getChatList() == null)
//            return null;
//        for (Chat chat: getUserByName(myUsername).getChatList()) {
//            if (chat.getUserOneName().equals(friend) || chat.getUserTwoName().equals(friend)) {
//                return chat;
//            }
//        }
//        return null;
//    }

    public static void initialize() {
        if (initialized) {
            return;
        }
        LocalData.users.add(new User( "yagami", "yyy", "Light", R.drawable.yagami));
        LocalData.users.add(new User( "L", "lll", "L", R.drawable.lprofile));
        LocalData.users.add(new User( "watari", "www", "watari", R.drawable.watari));
        LocalData.users.add(new User( "misa", "mmm", "misa-misa", R.drawable.misa));
        LocalData.users.add(new User( "matsuda", "mmm", "matsuda", R.drawable.matsuda));

        User yagami = LocalData.users.get(0);
        User L = LocalData.users.get(1);
        User watari = LocalData.users.get(2);
        User misa = LocalData.users.get(3);
        User matsuda = LocalData.users.get(4);

//        yagami.getChatList().add(new Chat(1, yagami, L, new ArrayList<>()));
//        L.getChatList().add(new Chat(1, L, yagami, new ArrayList<>()));
//
//        yagami.getChatList().add(new Chat(1, yagami, misa, new ArrayList<>()));
//        misa.getChatList().add(new Chat(1, misa, yagami, new ArrayList<>()));
//
//        misa.getChatList().add(new Chat(1, misa, L, new ArrayList<>()));
//        L.getChatList().add(new Chat(1, L, misa, new ArrayList<>()));
//
//        watari.getChatList().add(new Chat(1, watari, L, new ArrayList<>()));
//        L.getChatList().add(new Chat(1, L, watari, new ArrayList<>()));
//
//        watari.getChatList().add(new Chat(1, watari, matsuda, new ArrayList<>()));
//        matsuda.getChatList().add(new Chat(1, matsuda, watari, new ArrayList<>()));

//        easyMessage(misa.getUsername(), yagami.getUsername(), "lighttt i want a date!!");
//        easyMessage(yagami.getUsername(),misa.getUsername() , "im too busy for that today, maybe tomorrow?");
//        easyMessage(misa.getUsername(), yagami.getUsername(), "yay! ok, im going to plan it");






        initialized = true;
    }

//    public static void easyMessage(String from, String to, String content){
//        Date date = new Date();
//        Message message = new Message(1, date, content, from);
//        Chat chat1 = LocalData.getChatByName(from, to);
//        Chat chat2 = LocalData.getChatByName(to, from);
//
////        chat1.getMsgList().add(message);
////        chat2.getMsgList().add(message);
//
//    }



}
