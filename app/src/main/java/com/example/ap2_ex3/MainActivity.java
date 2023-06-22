package com.example.ap2_ex3;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<Chat> chats;
    ChatAdapter chatAdapter;
    String myUsername;

    private ChatsViewModel chatsViewModel;

    @Override
    public void onStart() {
        super.onStart();
        chatAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chats.sort(new SortbyLastMsg());
        ListView lstFeed = (ListView) findViewById(R.id.myChatsArea);

        User currentUser = LocalData.getUserByName(myUsername);

        if (chats == null) {
            chats = new ArrayList<>();
        }
        chatAdapter = new ChatAdapter(chats, currentUser);
        lstFeed.setAdapter(chatAdapter);
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
                            for (Chat chat : chats) {
                                if (chat.getUserOne().getUsername().equals(friendname) || chat.getUserTwo().getUsername().equals(friendname)) {
                                    return;
                                }
                            }
                            for (User user : LocalData.users) {
                                if (user.getUsername().equals(friendname)) {
                                    LocalData.getUserByName(myUsername).getChatList().add(0, new Chat(0, user, LocalData.getUserByName(myUsername), new ArrayList<>()));
                                    user.getChatList().add(0, new Chat(0, LocalData.getUserByName(myUsername), user, new ArrayList<>()));
                                    break;
                                }
                            }

                            chatsViewModel.getChatsLiveData().setValue(LocalData.getUserByName(myUsername).getChatList());

//                            chatAdapter.notifyDataSetChanged();
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
        } else
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
    }

    class SortbyLastMsg implements Comparator<Chat> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Chat a, Chat b) {
//            if (a.getLastMessage() == null)
//                return 1;
//            if (b.getLastMessage() == null) {
//                return 0;
//            }
//            int answer = b.getLastMessage().getDate().compareTo(a.getLastMessage().getDate());
//            return answer;

            Date dateA;
            Date dateB;
            if (a.getLastMessage() == null && b.getLastMessage() == null) {
                dateA = new Date(0);
                dateB = new Date(0);
            } else if (a.getLastMessage() == null && b.getLastMessage() != null) {
                dateA = new Date(0);
                dateB = b.getLastMessage().getDate();
            } else if (b.getLastMessage() == null && a.getLastMessage() != null) {
                dateA = a.getLastMessage().getDate();
                dateB = new Date(0);
            } else {
                dateA = a.getLastMessage().getDate();
                dateB = b.getLastMessage().getDate();
            }

            return dateB.compareTo(dateA);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        myUsername = intent.getStringExtra("user");
        User currentUser = LocalData.getUserByName(myUsername);
        chats = currentUser.getChatList();

        if (chats == null) {
            chats = new ArrayList<>();
        }

        ListView lstFeed = (ListView) findViewById(R.id.myChatsArea);
        chatAdapter = new ChatAdapter(chats, currentUser);
        lstFeed.setAdapter(chatAdapter);

        this.chatsViewModel = new ViewModelProvider(this).get(ChatsViewModel.class);
        this.chatsViewModel.getChatsLiveData().setValue(chats);
        this.chatsViewModel.getChatsLiveData().observe(this, data -> {
//                    chatAdapter = new ChatAdapter(data, currentUser);
                    lstFeed.setAdapter(chatAdapter);
                }
        );


//        lstFeed.setOnItemClickListener((parent, view, position, id) -> {
//            Chat c = chats.get(position);
//            c.select();
//            chatAdapter.notifyDataSetChanged();
//        });
    }

}