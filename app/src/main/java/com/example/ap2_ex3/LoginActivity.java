package com.example.ap2_ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

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
        }
        else
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
            String passowrd = passwordEtLogin.getText().toString();
//            if (usernameEtLogin.getText().toString().equals("") || true) {
//                changeBorderColor();
//
//            }
            for (User user: LocalData.users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(passowrd)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("user", username);
                    startActivity(intent);
                    break;
                }
            }

        });



    }

//    private void changeBorderColor() {
//        EditText usernameEtLogin = findViewById(R.id.usernameEtLogin);
//
//        // Get the current background drawable of the EditText
//        Drawable backgroundDrawable = usernameEtLogin.getBackground().mutate();
//
//        // Create a new drawable with the same shape as the background
//        Drawable borderDrawable = getResources().getDrawable(R.drawable.edittext_border).mutate();
//
//        // Set the color filter on the border drawable
//        borderDrawable.setColorFilter(Color.parseColor("#00349875") ,PorterDuff.Mode.SRC_IN);
//
//        // Create a LayerDrawable with the background and border drawables
//        Drawable[] layers = new Drawable[] {backgroundDrawable, borderDrawable};
//        LayerDrawable layerDrawable = new LayerDrawable(layers);
//
//        // Set the LayerDrawable as the background of the EditText
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            usernameEtLogin.setBackground(layerDrawable);
//        } else {
//            usernameEtLogin.setBackgroundDrawable(layerDrawable);
//        }
//    }
}