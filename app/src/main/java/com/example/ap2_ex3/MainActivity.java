package com.example.ap2_ex3;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

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

import com.example.ap2_ex3.Users.UserRegisterReqAndRes;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
//
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//

public class MainActivity extends AppCompatActivity {

    AppDB appDB;
    UserDao userDao;
    ChatDao chatDao;


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

        User currentUser = userDao.get(myUsername);
        chats = currentUser.getChatList();

        chats.sort(new SortbyLastMsg());
        ListView lstFeed = (ListView) findViewById(R.id.myChatsArea);

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

                            if (friendname.equals(myUsername)){
                                showDialog();
                                return; //don't add yourself
                            }

                            for (Chat chat : chats) {
                                if (chat.getUserOneName().equals(friendname) || chat.getUserTwoName().equals(friendname)) {
                                    return;
                                }
                            }
                            for (User user : userDao.index()) {
                                if (user.getUsername().equals(friendname)) {
//                                    LocalData.getUserByName(myUsername).getChatList().add(0, new Chat(0, user, LocalData.getUserByName(myUsername), new ArrayList<>()));
//                                    user.getChatList().add(0, new Chat(0, LocalData.getUserByName(myUsername), user, new ArrayList<>()));
                                    easyNewChat(userDao.get(myUsername), user);
                                    break;
                                }
                            }

                            chatsViewModel.getChatsLiveData().setValue(userDao.get(myUsername).getChatList());

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

        appDB = AppDB.getDBInstance(getApplicationContext());

        userDao = appDB.userDao();
        chatDao = appDB.chatDao();

        Intent intent = getIntent();
        myUsername = intent.getStringExtra("user");
        User currentUser = userDao.get(myUsername);
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
            chatAdapter = new ChatAdapter(data, currentUser);
            lstFeed.setAdapter(chatAdapter);
                }
        );
        lstFeed.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteChatDialog(position); // Show the delete chat dialog
            return true; // Consume the long-press event
        });

        //


        ChatsAPI chatsAPI = new ChatsAPI(this.chatsViewModel.getChatsLiveData(),this.userDao);
//        chatsAPI.getChatsListByName(myUsername);
        createUser(myUsername);




        //

    }

    public void createUser(String myName) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create()).build();
        WebServiceAPI webServiceAPI = retrofit.create(WebServiceAPI.class);


        webServiceAPI.createUser(new UserRegisterReqAndRes("asa", "asa", "asa", "asa"))
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

                        int a = 0;
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        int b = 0;

                    }
                });

    }


    void easyNewChat(User a, User b) {
        Chat chat;

        chat = new Chat(a.getUsername(), b.getUsername(), new ArrayList<>());
        chatDao.insert(chat);


        User userA = userDao.get(a.getUsername());
        List<Chat> temp = userA.getChatList();
        temp.add(chat);
        userA.setChatList(temp);
        userDao.update(userA);


        User userB = userDao.get(b.getUsername());
        temp = userB.getChatList();
        temp.add(chat);
        userB.setChatList(temp);
        userDao.update(userB);


//        LocalData.getUserByName(a.getUsername()).getChatList().add(chat);
//        LocalData.getUserByName(b.getUsername()).getChatList().add(chat);
    }

    @Override
    public void onBackPressed() {
        // Check if the current screen is the main screen
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.Confirmation)
                .setMessage(R.string.log_out)
                .setPositiveButton(R.string.Yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showDeleteChatDialog(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Chat")
                .setIcon(R.drawable.ic_delete) // Set the trash icon
                .setMessage("Are you sure you want to delete this chat?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Perform delete operation here for the chat at the given position
                    User me = userDao.get(myUsername);
                    List<Chat> myChats = me.getChatList();
                    Chat deleteChat = myChats.get(pos);

                    String user0ne = deleteChat.getUserOneName();
                    String userTwo = deleteChat.getUserTwoName();
                    String friend;
                    if (user0ne.equals(myUsername)) {
                        friend = userTwo;
                    } else {
                        friend = user0ne;
                    }

                    myChats.remove(pos);
                    chats = myChats;
                    me.setChatList(myChats);
                    userDao.update(me);

                    User you = userDao.get(friend);
                    List<Chat> yourChats = you.getChatList();

                    for (Chat chat : yourChats) {
                        if (chat.getUserOneName().equals(myUsername)
                                || chat.getUserTwoName().equals(myUsername)) {
                            yourChats.remove(chat);
                            you.setChatList(yourChats);
                            userDao.update(you);
                            break;
                        }
                    }


                    this.chatsViewModel.getChatsLiveData().setValue(chats);


                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Error")
                .setMessage("you cant add yourself!")
                .setPositiveButton("Okay",(dialog, which) -> dialog.dismiss())
                .show();
    }

}