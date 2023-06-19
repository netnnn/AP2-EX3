package com.example.ap2_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Chat> chats;

    private ChatsViewModel chatsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lstFeed = (ListView) findViewById(R.id.myChatsArea);

        Intent intent = getIntent();
        User currentUser = LocalData.getUserByName(intent.getStringExtra("user"));
        chats = currentUser.getChatList();
        if (chats == null) {
            chats = new ArrayList<>();
        }
        final ChatAdapter chatAdapter = new ChatAdapter(chats, currentUser);
        lstFeed.setAdapter(chatAdapter);
        lstFeed.setOnItemClickListener((parent, view, position, id) -> {
            Chat c = chats.get(position);
            c.select();
            chatAdapter.notifyDataSetChanged();
        });
    }

    private List<Chat> generateChats() {
        List<Chat> chats = new ArrayList<>();
        User userOne = new User(1, "userOne", "password1", "displayName1", 2);
        User userTwo = new User(1, "userTwo", "password2", "displayName2", 3);
        Date date = new Date();
        Message message = new Message(1, date, "hello world", userOne);
        List<Message> msgList = new ArrayList<>();
        msgList.add(message);
        chats.add(new Chat(1, userOne, userTwo, msgList));
        chats.add(new Chat(2, userTwo, userOne, msgList));
        chats.add(new Chat(3, userOne, userTwo, msgList));
        chats.add(new Chat(4, userTwo, userOne, msgList));
        chats.add(new Chat(5, userOne, userTwo, msgList));

        return chats;
    }
}