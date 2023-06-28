package com.example.ap2_ex3;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ChatsViewModel extends ViewModel {

    private MutableLiveData<List<Chat>> ChatsLiveData;
//    private ChatRepository chatRepository;


    public MutableLiveData<List<Chat>> getChatsLiveData(){
        if (ChatsLiveData == null){
            ChatsLiveData = new MutableLiveData<>();
        }
        return ChatsLiveData;

    }

//    public ChatsViewModel(){
//        this.chatRepository = new ChatRepository();
////        this.chats = chatRepository.getMyChats();
//    }

//    public addChat(Chat chat){
//        this.chatRepository.add(chat);
//    }
//
//    public deleteChat(Chat chat){
//        this.chatRepository.delete(chat);
//    }
//
//    public reload(){
//        this.chatRepository.reload();
//    }
}
