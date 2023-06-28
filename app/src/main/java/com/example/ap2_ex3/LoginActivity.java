package com.example.ap2_ex3;

import androidx.appcompat.app.AppCompatActivity;
// notice duplicate
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;

import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private static final int POST_NOTIFICATIONS_REQUEST_CODE = 1003;

    AppDB appDB;
    UserDao userDao;

    ChatDao chatDao;
    SettingsDao settingsDao;

    private static final int CAMERA_REQUEST_CODE = 1001;
    private static final int GALLERY_REQUEST_CODE = 1002;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        EditText usernameEtLogin = findViewById(R.id.usernameEtLogin);
        EditText passwordEtLogin = findViewById(R.id.passwordEtLogin);
        usernameEtLogin.setText("");
        passwordEtLogin.setText("");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, POST_NOTIFICATIONS_REQUEST_CODE);
        }

        //Print the firebase token for this device
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w("w", "Fetching FCM registration token failed", task.getException());
                        return;
                    }
                    // Get new FCM registration token
                    String token = task.getResult();
                    Log.d("d", token);
                });


        initialize();


//        LocalData.initialize();


        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.Login_title);

        Button LoginBtn = findViewById(R.id.LoginBtn);
        LoginBtn.setBackgroundColor(Color.parseColor("#5900FF"));
        EditText usernameEtLogin = findViewById(R.id.usernameEtLogin);
        EditText passwordEtLogin = findViewById(R.id.passwordEtLogin);

        LinearLayout goToRegisterBtn = findViewById(R.id.goToRegisterBtn);
        goToRegisterBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        LoginBtn.setOnClickListener(view -> {
            String username = usernameEtLogin.getText().toString();
            String password = passwordEtLogin.getText().toString();
            if (usernameEtLogin.getText().toString().equals("") || passwordEtLogin.getText().toString().equals("")) {
                if (usernameEtLogin.getText().toString().equals("")) {
                    usernameEtLogin.setHintTextColor(Color.RED);
                } else {
                    usernameEtLogin.setHintTextColor(Color.BLACK);
                }
                if (passwordEtLogin.getText().toString().equals("")) {
                    passwordEtLogin.setHintTextColor(Color.RED);
                } else {
                    passwordEtLogin.setHintTextColor(Color.BLACK);
                }
                return;
            }
            for (User user : userDao.index()) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("user", username);
                    startActivity(intent);
                    return;
                }
            }

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_register_error);

            TextView dialogTextView = dialog.findViewById(R.id.error);
            dialogTextView.setText(R.string.username_or_password_are_incorrect);
            Button dialogButton = dialog.findViewById(R.id.dialogButton);

            dialogButton.setOnClickListener(v -> dialog.dismiss());
            dialog.show();

        });

    }

    void initialize() {
        appDB = AppDB.getDBInstance(getApplicationContext());
        userDao = appDB.userDao();
        chatDao = appDB.chatDao();
        settingsDao = appDB.settingsDao();

        String url = settingsDao.getURL();
        if (url == null){
            url = "http://10.0.2.2:5000";
            boolean darkMode = false;
            boolean firstInit = true;

            Settings settings = new Settings(url, darkMode, firstInit);
            settingsDao.insert(settings);
        }


        if (settingsDao.getDarkMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        Settings settings = settingsDao.index();

        if (settings.isFirstInit()) {
            User yagami = new User("yagami", "yyy", "Light", R.drawable.yagami);
            User L = new User("L", "lll", "L", R.drawable.lprofile);
            User watari = new User("watari", "www", "watari", R.drawable.watari);
            User misa = new User("misa", "mmm", "misa-misa", R.drawable.misa);
            User matsuda = new User("matsuda", "mmm", "matsuda", R.drawable.matsuda);

//            userDao.deleteAll(); //clean


            userDao.insert(yagami, L, watari, misa, matsuda);

            easyNewChat(yagami, L);
            easyNewChat(yagami, misa);
            easyNewChat(misa, L);
            easyNewChat(watari, L);
            easyNewChat(watari, matsuda);

            easyMessage(misa.getUsername(), yagami.getUsername(), "lightttt where are you?");
            easyMessage(yagami.getUsername(), misa.getUsername(), "im busy now, talk to you later");
            easyMessage(misa.getUsername(), yagami.getUsername(), "you are always like that!");

            Settings settings1 = settingsDao.index();
            settings1.setFirstInit(false);
            settingsDao.update(settings1);
        }

        //LocalData
//        LocalData.users.add(yagami);
//        LocalData.users.add(L);
//        LocalData.users.add(watari);
//        LocalData.users.add(misa);
//        LocalData.users.add(matsuda);


//        User yagami1 = userDao.get("yagami");
//        yagami.setPassword("coco");
//        userDao.update(yagami);

//        List<User> userList = appDB.userDao().index();
//        for (User u : userList) {
//            Log.d("users", u.getUsername() + " " + u.getPassword());
//        }

//        appDB.chatDao().deleteAll(); //clean


//        List<Chat> chatList = appDB.chatDao().index();
//
//        for (Chat c : chatList) {
//            Log.d("chats", c.getUserOneName() + " " + c.getUserTwoName());
//        }




    }

    void easyNewChat(User a, User b) {
        Chat chat;
        User user;

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