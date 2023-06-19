package com.example.ap2_ex3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1001;
    private static final int GALLERY_REQUEST_CODE = 1002;
    private final Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FloatingActionButton settingsBtn = findViewById(R.id.settingsButton);
        settingsBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });

        Button uploadPicBtn = findViewById(R.id.uploadProfileBtn);
        uploadPicBtn.setOnClickListener(view -> {

            // Create an AlertDialog.Builder instance
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            View buttonsView = LayoutInflater.from(this).inflate(R.layout.dialog_button_icon, null);
            ImageView positiveButtonIcon = buttonsView.findViewById(R.id.camera_button_icon);
            positiveButtonIcon.setImageResource(R.drawable.ic_camera);
            builder.setPositiveButton(null, null);
            builder.setView(buttonsView);
            AlertDialog dialog = builder.create();

// Set the negative button
//        View negativeButtonView = LayoutInflater.from(this).inflate(R.layout.dialog_button_icon, null);
            ImageView negativeButtonIcon = buttonsView.findViewById(R.id.gallery_button_icon);
            negativeButtonIcon.setImageResource(R.drawable.ic_gallery);
            builder.setNegativeButton(null, null);

            positiveButtonIcon.setOnClickListener(v -> {
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                                == PackageManager.PERMISSION_DENIED) {
                            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                        }
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                        dialog.dismiss();
                    }
            );

            negativeButtonIcon.setOnClickListener(views -> {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_REQUEST_CODE);
                }
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
                dialog.dismiss();
            });
            ;

// Create and show the dialog
            dialog.show();


        });

        Button registerBtn = findViewById(R.id.signUpBtn);
        registerBtn.setOnClickListener(view -> {
            EditText usernameEt = findViewById(R.id.usernameEt);
            EditText PasswordEt = findViewById(R.id.PasswordEt);
            EditText DisplayNameEt = findViewById(R.id.DisplayNameEt);
            ImageView profileIv = findViewById(R.id.profileIv);

            String username = usernameEt.getText().toString();
            String password = PasswordEt.getText().toString();
            String displayName = DisplayNameEt.getText().toString();
            //profileIv.getDrawable();

            User newUser = new User(0, username, password, displayName, 3);
            LocalData.users.add(newUser);

            finish();
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView IV = findViewById(R.id.profileIv);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            IV.setImageBitmap(photo);
        }

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            IV.setImageURI(selectedImageUri);
        }

    }
}