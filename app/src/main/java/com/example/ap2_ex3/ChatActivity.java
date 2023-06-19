package com.example.ap2_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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

        messages = generateMessages();
        final MessageAdapter messageAdapter = new MessageAdapter(messages);
        lstFeed.setAdapter(messageAdapter);

        EditText newMsg = findViewById(R.id.enterMessage);
        FloatingActionButton sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(view -> {

            messages.add(new Message(5, new Date(), newMsg.getText().toString(), new User(1, "userOne", "password1", "displayName1", 2)));
            messageAdapter.notifyDataSetChanged();
        });
    }

    private List<Message> generateMessages() {
        User userOne = new User(1, "userOne", "password1", "displayName1", 2);
        User userTwo = new User(1, "userTwo", "password2", "displayName2", 3);
        Date date = new Date();
        List<Message> messages = new ArrayList<>();
        Message message = new Message(1, date, "hello world", userOne);
        Date date2 = new Date();
        Message message2 = new Message(2, date2, "hello world2", userTwo);
        Date date3 = new Date();
        Message message3 = new Message(3, date3, "hello world3", userOne);
        Date date4 = new Date();
        Message message4 = new Message(4, date4, "hello world4", userTwo);

        messages.add(message);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);


        return messages;
    }
}
