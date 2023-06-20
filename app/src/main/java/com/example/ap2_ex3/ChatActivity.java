package com.example.ap2_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    List<Message> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ListView lstFeed = (ListView) findViewById(R.id.myMessagesArea);

        Intent intent = getIntent();
        String myUsername = intent.getStringExtra("name");
        int position = intent.getIntExtra("position", 0);

        messages = LocalData.getUserByName(myUsername).getChatList().get(position).getMsgList();
        final MessageAdapter messageAdapter = new MessageAdapter(messages, myUsername);
        lstFeed.setAdapter(messageAdapter);

        EditText newMsg = findViewById(R.id.enterMessage);
        FloatingActionButton sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(view -> {
            String friendName = getOtherUser(LocalData.getUserByName(myUsername).getChatList().get(position), myUsername);
            LocalData.easyMessage(myUsername, friendName, newMsg.getText().toString());

            messageAdapter.notifyDataSetChanged();
            newMsg.setText("");

        });
        lstFeed.smoothScrollToPosition(lstFeed.getCount() - 1);
    }

    public String getOtherUser(Chat chat, String myUsername) {
        if (chat.getUserOne().getUsername().equals(myUsername)){
            return chat.getUserTwo().getUsername();
        }
        return chat.getUserOne().getUsername();
    }
}
