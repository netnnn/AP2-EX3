package com.example.ap2_ex3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Chat {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "userOneName")
    private String userOneName;
    @ColumnInfo(name = "userTwoName")
    private String userTwoName;
    @ColumnInfo(name = "msgList")
    private List<Message> msgList;

    public Chat(String userOneName, String userTwoName, List<Message> msgList) {
        this.userOneName = userOneName;
        this.userTwoName = userTwoName;
        this.msgList = msgList;
        if (this.msgList == null) {
            this.msgList = new ArrayList<>();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserOneName() {
        return userOneName;
    }

    public void setUserOneName(String userOneName) {
        this.userOneName = userOneName;
    }

    public String getUserTwoName() {
        return userTwoName;
    }

    public void setUserTwoName(String userTwoName) {
        this.userTwoName = userTwoName;
    }

    public List<Message> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<Message> msgList) {
        this.msgList = msgList;
    }

    public Message getLastMessage() {
        if (this.msgList.size() == 0) {
            return null;
        }
        return this.msgList.get(this.msgList.size() - 1);
    }

    public void addMsg(Message msg){
        this.msgList.add(msg);
    }
}
