package com.example.ap2_ex3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ChatsViewModel extends ViewModel {

    private MutableLiveData<List<Chat>> ChatsLiveData;

    public MutableLiveData<List<Chat>> getChatsLiveData(){
        if (ChatsLiveData == null){
            ChatsLiveData = new MutableLiveData<>();
        }
        return ChatsLiveData;
    }
}
