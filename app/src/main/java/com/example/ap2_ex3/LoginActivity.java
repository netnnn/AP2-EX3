package com.example.ap2_ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

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



        LocalData.initialize();

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
            for (User user : LocalData.users) {
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
}