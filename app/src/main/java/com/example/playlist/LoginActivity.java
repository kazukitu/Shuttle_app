package com.example.playlist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 100;

    private DatabaseHelper databaseHelper;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button googleLoginButton;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        databaseHelper = new DatabaseHelper(this);


        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.normalLoginButton);
        googleLoginButton = findViewById(R.id.googleLoginButton);

        // Initialize Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                normalLogin();
            }
        });

        // Google login button listener
        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogin();
            }
        });


        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


        Button displayUsersButton = findViewById(R.id.displayUsersButton);
        displayUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayUsers();
            }
        });
    }

    private void normalLogin() {
        String enteredUsername = usernameEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();

        Boolean userExist = databaseHelper.checkUser(enteredUsername, enteredPassword);
        Log.d("User Exists", String.valueOf(userExist));

        if (userExist) {
            // Login successful
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Login Failed, Please Check Username and Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void googleLogin() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (ApiException e) {

            Toast.makeText(LoginActivity.this, "Google Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayUsers() {
        // Display users logic...
    }
}
