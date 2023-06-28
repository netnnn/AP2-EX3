//package com.example.ap2_ex3;
//
//import android.app.Application;
//import android.content.Context;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class ChatRepository {
//
//    private ChatsAPI chatsAPI;
//    private UserDao userDao;
//    private ChatsListData chatsListData;
//
//    public ChatRepository() {
//        AppDB appDB = AppDB.getDBInstance();
//        userDao = appDB.userDao();
//        chatsListData = new ChatsListData();
//        this.chatsAPI = new ChatsAPI(chatsListData, userDao);
//    }
//
//    class ChatsListData extends MutableLiveData<List<Chat>>{
//
//        public ChatsListData(){
//            super();
//            setValue(new LinkedList<>());
//        }
//
//        @Override
//        protected void onActive(){
//            super.onActive();
//            new Thread(() -> {
////                chatsListData.postValue(userDao.get(my name));
//            }).start();
//        }
//    }
//
//    public LiveData<List<Chat>> getAllChats(){
//        return chatsListData;
//    }
//
//}
