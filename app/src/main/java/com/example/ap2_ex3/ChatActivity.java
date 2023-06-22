package com.example.ap2_ex3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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

public class ChatActivity extends AppCompatActivity {
    List<Message> messages;

    private MessageViewModel messageViewModel;

    private MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ListView lstFeed = (ListView) findViewById(R.id.myMessagesArea); // Replace `listView` with the ID of your ListView

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.chat_action_bar);

        ImageView iv = findViewById(R.id.friendProfile);
        TextView tv = findViewById(R.id.currentFriendName);
        Intent intent = getIntent();
        String myUsername = intent.getStringExtra("name");
        String friendUserName = intent.getStringExtra("friendname");
        tv.setText(LocalData.getUserByName(friendUserName).getDisplayName());
        if (LocalData.getUserByName(friendUserName).getdPicture() == null){
            iv.setImageResource(LocalData.getUserByName(friendUserName).getPicture());
        } else {
            iv.setImageDrawable(LocalData.getUserByName(friendUserName).getdPicture());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ACTION BAR
        setTitle(LocalData.getUserByName(friendUserName).getDisplayName());
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
//                | ActionBar.DISPLAY_SHOW_CUSTOM);
//        ImageView friendProfile = new ImageView(actionBar.getThemedContext());
//        friendProfile.setScaleType(ImageView.ScaleType.CENTER);
//        if (LocalData.getUserByName(getIntent().getStringExtra("friendname")).getPicture() == 0) {
//            friendProfile.setImageDrawable(LocalData
//                    .getUserByName(getIntent().getStringExtra("friendname")).getdPicture());
//        } else {
//            friendProfile.setImageResource(LocalData
//                    .getUserByName(getIntent().getStringExtra("friendname")).getPicture());
//        }
//        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
//                200,200,Gravity.END | Gravity.CENTER_VERTICAL);
//        layoutParams.rightMargin = 40;
//        friendProfile.setLayoutParams(layoutParams);
//        actionBar.setCustomView(friendProfile);

        int position = intent.getIntExtra("position", 0);

        messages = LocalData.getUserByName(myUsername).getChatList().get(position).getMsgList();
        this.messageAdapter = new MessageAdapter(messages, myUsername);
        lstFeed.setAdapter(messageAdapter);

        this.messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        this.messageViewModel.getMessagesLiveData().setValue(messages);
        this.messageViewModel.getMessagesLiveData().observe(this, data -> {
            lstFeed.setAdapter(messageAdapter);
        });




        EditText newMsg = findViewById(R.id.enterMessage);
        FloatingActionButton sendButton = findViewById(R.id.sendButton);
        sendButton.setBackgroundColor(Color.parseColor("#5900FF"));


        sendButton.setOnClickListener(view -> {
            String friendName = getOtherUser(LocalData.getUserByName(myUsername).getChatList().get(position), myUsername);
            String[] str = newMsg.getText().toString().split(" ");
            if (str.length == 0 || newMsg.getText().toString().equals("")) {
                return;
            }
            int currentPosition = lstFeed.getFirstVisiblePosition();
            View topView = lstFeed.getChildAt(0);
            int offset = (topView == null) ? 0 : (topView.getTop() - lstFeed.getPaddingTop());


            LocalData.easyMessage(myUsername, friendName, newMsg.getText().toString());

//            messageAdapter.notifyDataSetChanged();
            this.messageViewModel.getMessagesLiveData().setValue(LocalData.getUserByName(myUsername).getChatList().get(position).getMsgList());
            newMsg.setText("");
            lstFeed.setSelectionFromTop(currentPosition, offset);
            lstFeed.post(() -> lstFeed.setSelection(lstFeed.getCount() - 1));
        });
        lstFeed.post(() -> lstFeed.setSelection(lstFeed.getCount()));
    }

    public String getOtherUser(Chat chat, String myUsername) {
        if (chat.getUserOne().getUsername().equals(myUsername)){
            return chat.getUserTwo().getUsername();
        }
        return chat.getUserOne().getUsername();
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
}
