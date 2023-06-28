package com.example.ap2_ex3.Chats;

import com.example.ap2_ex3.Users.GetUserRes;

public class ChatTileData {
    int id;
    GetUserRes userData;
    LastMsgData lastMsgData;

    public ChatTileData(int id, GetUserRes userData, LastMsgData lastMsgData) {
        this.id = id;
        this.userData = userData;
        this.lastMsgData = lastMsgData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GetUserRes getUserData() {
        return userData;
    }

    public void setUserData(GetUserRes userData) {
        this.userData = userData;
    }

    public LastMsgData getLastMsgData() {
        return lastMsgData;
    }

    public void setLastMsgData(LastMsgData lastMsgData) {
        this.lastMsgData = lastMsgData;
    }

    //
    class LastMsgData {
        int id;
        String created, content;
        public LastMsgData(int id, String created, String content) {
            this.id = id;
            this.created = created;
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


}
