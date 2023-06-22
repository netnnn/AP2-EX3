package com.example.ap2_ex3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private User userOne;

    private User userTwo;

    private List<Message> msgList;

    public Chat(int id, User userOne,User userTwo,List<Message> msgList ){
        this.id = id;
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.msgList = msgList;
        if (this.msgList == null){
            this.msgList = new ArrayList<>();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserOne() {
        return userOne;
    }

    public void setUserOne(User userOne) {
        this.userOne = userOne;
    }

    public User getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(User userTwo) {
        this.userTwo = userTwo;
    }

    public List<Message> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Message> msgList) {
        this.msgList = msgList;
    }

    public Message getLastMessage(){
        if (this.msgList.size() == 0){
            return null;
        }
        return this.msgList.get(this.msgList.size()-1);
    }
}
