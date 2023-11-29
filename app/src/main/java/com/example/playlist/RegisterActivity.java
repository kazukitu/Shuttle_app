package com.example.playlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;
    private Button registerButton;

    private DatabaseHelper databaseHelper;


    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        repeatPasswordEditText = findViewById(R.id.repeatPasswordEditText);
        registerButton = findViewById(R.id.registerButton);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        databaseHelper = new DatabaseHelper(this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String repeatPassword = repeatPasswordEditText.getText().toString();

                if (password.equals(repeatPassword)) {

                    boolean isInserted = databaseHelper.insertUserData(username, password);

                    if (isInserted) {

                        showMessage("Registration successful");


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Log.d("User List", "List of Users:");
                                Cursor cursor = databaseHelper.getAllUserData();
                                if (cursor != null && cursor.getCount() > 0) {
                                    while (cursor.moveToNext()) {
                                        String userId = cursor.getString(0);
                                        String userName = cursor.getString(1);
                                        String userPassword = cursor.getString(2);
                                        Log.d("User List", "User ID: " + userId + ", Username: " + userName + ", Password: " + userPassword);
                                    }
                                    cursor.close();
                                }


                                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(loginIntent);
                                finish();
                            }
                        }, 3000);
                    } else {

                        showMessage("Registration failed");
                    }
                } else {

                    showMessage("Passwords do not match");
                }
            }
        });
    }
}
