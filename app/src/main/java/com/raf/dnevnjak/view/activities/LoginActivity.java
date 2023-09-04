package com.raf.dnevnjak.view.activities;

import static com.raf.dnevnjak.view.activities.MainActivity.EMAIL_KEY;
import static com.raf.dnevnjak.view.activities.MainActivity.IS_LOGGED_IN;
import static com.raf.dnevnjak.view.activities.MainActivity.USERNAME_KEY;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.raf.dnevnjak.R;
import com.raf.dnevnjak.database.SQLiteDB;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEdit;
    private EditText passwordEdit;
    private EditText emailEdit;
    private Button loginButton;
    public SQLiteDB SQLiteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDb = new SQLiteDB(this);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init() {
        if (!SQLiteDb.checkUsername("luka")) {
            SQLiteDb.insertData("luka", "password", "luka@raf.rs");
            System.out.println("INSERTING LUKA TO USERS");
        }
        initView();
        initListeners();
    }

    private void initView(){
        usernameEdit = findViewById(R.id.loginUsername);
        passwordEdit = findViewById(R.id.loginPassword);
        emailEdit = findViewById(R.id.loginEmail);
        loginButton = findViewById(R.id.loginButton);
        System.out.println("BUTTON: " + loginButton);
    }
    private void initListeners(){
        loginButton.setOnClickListener( v -> {
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            String email = emailEdit.getText().toString();
            if(!isValid(username, password, email)) return;

            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

            Boolean checkUserPass = SQLiteDb.checkUsernamePassword(username, password, email);
            if (checkUserPass){
                sharedPreferences
                        .edit()
                        .putString(IS_LOGGED_IN, "YES")
                        .putString(USERNAME_KEY, username)
                        .putString(EMAIL_KEY, email)
                        .apply();
                Toast.makeText(LoginActivity.this, "Log in successfull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return;
            }
            Toast.makeText(this, "INVALID CREDENTIALS", Toast.LENGTH_LONG).show();
        });
    }


    private boolean isValid(String username, String password, String email){
        if(username.trim().length() == 0 || password.trim().length() == 0 || email.trim().length() == 0){
            Toast.makeText(this, "All Fields Required", Toast.LENGTH_LONG).show();
            return false;
        }

        if(password.length() < 5){
            Toast.makeText(this, "Password must be at least 5 characters long", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
