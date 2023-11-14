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

    private DatabaseHelper databaseHelper; // 添加数据库帮助类


    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // 用户点击消息后，返回上一页（登录页面）
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish(); // 关闭注册页面
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
                // 当用户点击返回按钮时，返回到登录页面
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish(); // 关闭注册页面
            }
        });

        // 初始化数据库帮助类
        databaseHelper = new DatabaseHelper(this);



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理注册逻辑
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String repeatPassword = repeatPasswordEditText.getText().toString();

                if (password.equals(repeatPassword)) {
                    // 密码一致，可以注册
                    // 在这里处理注册逻辑，例如将用户信息保存到数据库
                    boolean isInserted = databaseHelper.insertUserData(username, password);

                    if (isInserted) {
                        // 注册成功后，显示成功消息
                        showMessage("Registration successful");

                        // 延迟2秒后关闭消息对话框并列出所有用户
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
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
                                }

                                // 注册成功后，跳转回登录页面
                                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(loginIntent);
                                finish(); // 关闭注册页面
                            }
                        }, 3000);
                    } else {
                        // 插入用户数据失败，显示错误消息
                        showMessage("Registration failed");
                    }
                } else {
                    // 密码不一致，显示错误消息
                    showMessage("Passwords do not match");
                }
            }
        });
    }
}
