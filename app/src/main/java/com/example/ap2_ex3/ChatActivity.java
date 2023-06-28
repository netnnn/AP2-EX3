package com.example.ap2_ex3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {
    List<Message> messages;
    AppDB appDB;
    UserDao userDao;
    ChatDao chatDao;
    MessageDao messageDao;
    User me;

    private MessageViewModel messageViewModel;

    private MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        appDB = AppDB.getDBInstance(getApplicationContext());
        userDao = appDB.userDao();
        chatDao = appDB.chatDao();
        messageDao = appDB.messageDao();

        //

        //

        ListView lstFeed = (ListView) findViewById(R.id.myMessagesArea); // Replace `listView` with the ID of your ListView

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.chat_action_bar);

        ImageView iv = findViewById(R.id.friendProfile);
        TextView tv = findViewById(R.id.currentFriendName);
        Intent intent = getIntent();
        String myUsername = intent.getStringExtra("name");
        String friendUserName = intent.getStringExtra("friendname");

        me = userDao.get(myUsername);
        User friend = userDao.get(friendUserName);


        tv.setText(friend.getDisplayName());
        if (friend.getPicture() == 0){
            String encodedImage = friend.getBase64();
            byte[] decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            iv.setImageBitmap(bitmap);
        } else {
            iv.setImageResource(friend.getPicture());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ACTION BAR
        setTitle(friend.getDisplayName());

        messages = userDao.get(myUsername).findChatWith(friendUserName).getMsgList();
//        messages = me.getChatList().get(position).getMsgList();
        this.messageAdapter = new MessageAdapter(messages, myUsername);
        lstFeed.setAdapter(messageAdapter);

        this.messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        this.messageViewModel.getMessagesLiveData().setValue(messages);
        this.messageViewModel.getMessagesLiveData().observe(this, data -> {
            this.messageAdapter = new MessageAdapter(data, myUsername);
            lstFeed.setAdapter(messageAdapter);
        });


        EditText newMsg = findViewById(R.id.enterMessage);
        FloatingActionButton sendButton = findViewById(R.id.sendButton);
        sendButton.setBackgroundColor(Color.parseColor("#5900FF"));


        sendButton.setOnClickListener(view -> {
            String[] str = newMsg.getText().toString().split(" ");
            if (str.length == 0 || newMsg.getText().toString().equals("")) {
                return;
            }
            int currentPosition = lstFeed.getFirstVisiblePosition();
            View topView = lstFeed.getChildAt(0);
            int offset = (topView == null) ? 0 : (topView.getTop() - lstFeed.getPaddingTop());


            easyMessage(myUsername, friendUserName, newMsg.getText().toString());

            me = userDao.get(myUsername);

//            messageAdapter.notifyDataSetChanged();
            this.messageViewModel.getMessagesLiveData().setValue(me.findChatWith(friendUserName).getMsgList());
            newMsg.setText("");
            lstFeed.setSelectionFromTop(currentPosition, offset);
            lstFeed.post(() -> lstFeed.setSelection(lstFeed.getCount() - 1));
        });
        lstFeed.post(() -> lstFeed.setSelection(lstFeed.getCount()));
    }

    public String getOtherUser(Chat chat, String myUsername) {
        if (chat.getUserOneName().equals(myUsername)){
            return chat.getUserTwoName();
        }
        return chat.getUserOneName();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed(); // Handle back button press
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void easyMessage(String from, String to, String content) {
        Date date = new Date();
        Message message = new Message(date, content, from);

        User userA = userDao.get(from);
        Chat chat = userA.findChatWith(to);
        chat.addMsg(message);
        userDao.update(userA);

        User userB = userDao.get(to);
        chat = userB.findChatWith(from);
        chat.addMsg(message);
        userDao.update(userB);

    }
}
