package com.example.ap2_ex3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MessageViewModel extends ViewModel {

    private MutableLiveData<List<Message>> MessagesLiveData;

    public MutableLiveData<List<Message>> getMessagesLiveData(){
        if (MessagesLiveData == null){
            MessagesLiveData = new MutableLiveData<>();
        }
        return MessagesLiveData;
    }

}
