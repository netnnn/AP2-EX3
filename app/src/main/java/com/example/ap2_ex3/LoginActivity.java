package com.example.ap2_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button LoginBtn = findViewById(R.id.LoginBtn);
        EditText usernameEtLogin = findViewById(R.id.usernameEtLogin);
        EditText passwordEtLogin = findViewById(R.id.passwordEtLogin);

        FloatingActionButton settingsBtn = findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        LinearLayout goToRegisterBtn = findViewById(R.id.goToRegisterBtn);
        goToRegisterBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        LoginBtn.setOnClickListener(view -> {
            String username = usernameEtLogin.getText().toString();
            String passowrd = passwordEtLogin.getText().toString();
            for (User user: LocalData.users) {

                if (user.getUsername().equals(username) && user.getPassword().equals(passowrd)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("user", username);
                    startActivity(intent);
                    break;
                }
            }

        });

        LocalData.users.add(new User(0, "yagami", "yyy", "Light", R.drawable.yagami));
        LocalData.users.add(new User(1, "L", "lll", "L", R.drawable.lprofile));
        LocalData.users.add(new User(2, "watari", "www", "watari", R.drawable.watari));
        LocalData.users.add(new User(3, "misa", "mmm", "misa-misa", R.drawable.misa));
        LocalData.users.add(new User(4, "matsuda", "mmm", "matsuda", R.drawable.matsuda));

        User yagami = LocalData.users.get(0);
        User L = LocalData.users.get(1);
        User watari = LocalData.users.get(2);
        User misa = LocalData.users.get(3);
        User matsuda = LocalData.users.get(4);

        yagami.getChatList().add(new Chat(1, yagami, L, new ArrayList<>()));
        yagami.getChatList().add(new Chat(1, yagami, misa, new ArrayList<>()));

        misa.getChatList().add(new Chat(1, misa, yagami, new ArrayList<>()));
        misa.getChatList().add(new Chat(1, misa, L, new ArrayList<>()));

        watari.getChatList().add(new Chat(1, watari, L, new ArrayList<>()));
        watari.getChatList().add(new Chat(1, watari, matsuda, new ArrayList<>()));


    }
}