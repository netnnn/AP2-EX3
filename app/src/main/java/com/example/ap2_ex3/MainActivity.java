package com.example.ap2_ex3;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Chat> chats;
    ChatAdapter chatAdapter;
    String myUsername;

    private ChatsViewModel chatsViewModel;
//    private MutableLiveData<>

    @Override
    public void onStart() {
        super.onStart();
        chatAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chatsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_add_chat) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Get the layout inflater
            LayoutInflater inflater = this.getLayoutInflater();

            // Inflate and set the layout for the dialog
            View dialogview = inflater.inflate(R.layout.dialog_add_chat, null);
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(dialogview)
                    // Add action buttons
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            EditText box = dialogview.findViewById(R.id.newChat);
                            String friendname = box.getText().toString();
                            for (Chat chat: chats) {
                                if (chat.getUserOne().getUsername().equals(friendname) || chat.getUserTwo().getUsername().equals(friendname)){
                                    return;
                                }
                            }
                            for (User user: LocalData.users) {
                                if (user.getUsername().equals(friendname)){
                                    LocalData.getUserByName(myUsername).getChatList().add(new Chat(0, user, LocalData.getUserByName(myUsername), new ArrayList<>()));
                                    user.getChatList().add(new Chat(0, LocalData.getUserByName(myUsername), user, new ArrayList<>()));
                                    break;
                                }
                            }

                            chatAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            Dialog dialog = builder.create();
            dialog.show();
            return true;
        }
        else
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        myUsername = intent.getStringExtra("user");
        this.chatsViewModel = new ViewModelProvider(this).get(ChatsViewModel.class);
        this.chatsViewModel.getChatsLiveData().setValue(LocalData.getUserByName(myUsername).getChatList());






        setContentView(R.layout.activity_main);
        ListView lstFeed = (ListView) findViewById(R.id.myChatsArea);

        User currentUser = LocalData.getUserByName(myUsername);
        chats = currentUser.getChatList();
        if (chats == null) {
            chats = new ArrayList<>();
        }
        chatAdapter = new ChatAdapter(chats, currentUser);
        lstFeed.setAdapter(chatAdapter);
//        lstFeed.setOnItemClickListener((parent, view, position, id) -> {
//            Chat c = chats.get(position);
//            c.select();
//            chatAdapter.notifyDataSetChanged();
//        });
    }

}