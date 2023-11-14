package com.example.playlist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.playlist.MainActivity;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private void displayUsers() {
        // 查询数据库并打印用户信息到Log
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
        } else {
            Log.d("User List", "No users found in the database.");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);
        // 初始化界面元素
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.normalLoginButton);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当用户点击"Register"按钮时，跳转到注册页面
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        // 获取 Display Users 按钮
        Button displayUsersButton = findViewById(R.id.displayUsersButton);
        displayUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理显示用户信息的逻辑
                displayUsers();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入的用户名和密码
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // 查询数据库以获取用户名对应的MD5哈希密码
                Boolean userExisit = databaseHelper.checkUser(enteredUsername,enteredPassword);
                Log.d("User Exists", String.valueOf(userExisit));

                if (userExisit) {
                    // Login Successful
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed，Please Check Username and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
