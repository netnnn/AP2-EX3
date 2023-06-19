package com.example.ap2_ex3;

import java.util.List;

public class Chat {

    private int id;

    private User userOne;

    private User userTwo;

    private String lastMessage;

    private List<Message> msgList;

    public Chat(int id, User userOne,User userTwo,List<Message> msgList ){
        this.id = id;
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.msgList = msgList;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
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

    public void select() {
         lastMessage = lastMessage + " selected";
    }
}