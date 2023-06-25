package com.example.ap2_ex3;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    AppDB appDB;
    SettingsDao settingsDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        appDB = AppDB.getDBInstance(getApplicationContext());
        settingsDao = appDB.settingsDao();


        EditText url_server_ET = findViewById(R.id.url_server_ET);
        url_server_ET.setText(settingsDao.getURL());

        Button updateUrlButton = findViewById(R.id.updateUrlButton);
        updateUrlButton.setOnClickListener(view -> {
//            LocalData.serverUrl = url_server_ET.getText().toString();
            Settings settings = settingsDao.index();
            settings.setServerURL(url_server_ET.getText().toString());
            settingsDao.update(settings);
        });

        SwitchMaterial switchMode =findViewById(R.id.switch_mode);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.settings);
        TextView tv = findViewById(R.id.tv);

        switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                tv.setText(R.string.switch_text_to_bright);

                Settings settings = settingsDao.index();
                settings.setDarkMode(true);
                settingsDao.update(settings);

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                tv.setText(R.string.switch_text);

                Settings settings = settingsDao.index();
                settings.setDarkMode(false);
                settingsDao.update(settings);

            }
        });




//        boolean isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        boolean isNightModeOn = settingsDao.getDarkMode();
                switchMode.setChecked(isNightModeOn);
        if (isNightModeOn) {
            tv.setText(R.string.switch_text_to_bright);
            switchMode.setChecked(true);
        } else {
            tv.setText(R.string.switch_text);
            switchMode.setChecked(false);
        }




        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                switchMode.setChecked(true);
                tv.setText(R.string.switch_text_to_bright);
                break;
        }
    }

    @Override
    public void recreate() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        startActivity(getIntent());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
