package com.example.ap2_ex3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1001;
    private static final int GALLERY_REQUEST_CODE = 1002;
    private final Activity context = this;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.Register_title);

        Button uploadPicBtn = findViewById(R.id.uploadProfileBtn);
        uploadPicBtn.setBackgroundColor(Color.parseColor("#5900FF"));
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
        registerBtn.setBackgroundColor(Color.parseColor("#5900FF"));
        registerBtn.setOnClickListener(view -> {
            EditText usernameEt = findViewById(R.id.usernameEt);
            EditText PasswordEt = findViewById(R.id.PasswordEt);
            EditText verifyET = findViewById(R.id.VerifyPasswordEt);
            EditText DisplayNameEt = findViewById(R.id.DisplayNameEt);
            ImageView profileIv = findViewById(R.id.profileIv);

            String username = usernameEt.getText().toString();
            String password = PasswordEt.getText().toString();
            String verifyPassword = verifyET.getText().toString();
            String displayName = DisplayNameEt.getText().toString();

            if (!validate()) {
                return;
            }

            int maxId = 0;
            for (User user : LocalData.users) {
                for (Chat chat : user.getChatList()) {
                    if (maxId <= chat.getId()) {
                        maxId = chat.getId();
                    }
                }
            }
            User newUser = new User(maxId, username, password, displayName, profileIv.getDrawable());
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

    boolean validate() {
        EditText usernameEt = findViewById(R.id.usernameEt);
        EditText PasswordEt = findViewById(R.id.PasswordEt);
        EditText verifyET = findViewById(R.id.VerifyPasswordEt);
        EditText DisplayNameEt = findViewById(R.id.DisplayNameEt);
        ImageView profileIv = findViewById(R.id.profileIv);

        String username = usernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String verifyPassword = verifyET.getText().toString();
        String displayName = DisplayNameEt.getText().toString();

        if (username.equals("") || password.equals("") || verifyPassword.equals("") || displayName.equals("")) {
            if (username.equals("")) {
                usernameEt.setHintTextColor(Color.RED);
            } else {
                usernameEt.setHintTextColor(Color.BLACK);
            }
            if (password.equals("")) {
                PasswordEt.setHintTextColor(Color.RED);
            } else {
                PasswordEt.setHintTextColor(Color.BLACK);
            }
            if (verifyPassword.equals("")) {
                verifyET.setHintTextColor(Color.RED);
            } else {
                verifyET.setHintTextColor(Color.BLACK);
            }
            if (displayName.equals("")) {
                DisplayNameEt.setHintTextColor(Color.RED);
            } else {
                DisplayNameEt.setHintTextColor(Color.BLACK);
            }
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_register_error);

            TextView dialogTextView = dialog.findViewById(R.id.error);
            dialogTextView.setText(R.string.fields_are_empty);
            Button dialogButton = dialog.findViewById(R.id.dialogButton);

            dialogButton.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
            return false;
        }

        if (profileIv.getDrawable() == null) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_register_error);

            TextView dialogTextView = dialog.findViewById(R.id.error);
            dialogTextView.setText(R.string.please_upload_a_photo);
            Button dialogButton = dialog.findViewById(R.id.dialogButton);

            dialogButton.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
            return false;
        }

        if (password.length() < 7) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_register_error);

            TextView dialogTextView = dialog.findViewById(R.id.error);
            dialogTextView.setText(R.string.password_length);
            Button dialogButton = dialog.findViewById(R.id.dialogButton);

            dialogButton.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
            return false;
        }

        if (!password.equals(verifyPassword)) {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_register_error);

            TextView dialogTextView = dialog.findViewById(R.id.error);
            dialogTextView.setText(R.string.passwords_are_not_identical);
            Button dialogButton = dialog.findViewById(R.id.dialogButton);

            dialogButton.setOnClickListener(v -> dialog.dismiss());
            dialog.show();
            return false;
        }

        for (User user : LocalData.users) {
            if (user.getUsername().equals(username)) {
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_register_error);

                Button dialogButton = dialog.findViewById(R.id.dialogButton);

                dialogButton.setOnClickListener(v -> dialog.dismiss());
                dialog.show();
                return false;
            }
        }
        return true;
    }


}