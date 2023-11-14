package com.example.playlist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SplashActivity<FileChannel> extends AppCompatActivity {

    DatabaseHelper myDb;


    // Splash Delay
    private static final long SPLASH_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myDb = new DatabaseHelper(this);
        Log.i("MainActivity", String.valueOf(myDb));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}
