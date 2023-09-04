package com.raf.dnevnjak.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentTransaction;

import com.raf.dnevnjak.R;
import com.raf.dnevnjak.view.fragments.MainFragment;


public class MainActivity extends AppCompatActivity {

    public static final String IS_LOGGED_IN  = "userLoggedIn";
    public static final String USERNAME_KEY = "username_key";
    public static final String EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Handle the splash screen transition.

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            String message = sharedPreferences.getString(IS_LOGGED_IN, "");
            if(message == null || message == ""){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            return false;
        });
        Intent intent = getIntent();
        if(intent != null) {
            String userType = intent.getStringExtra(IS_LOGGED_IN);
            if(userType != null && !userType.equals("") ){
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                String message = sharedPreferences.getString(IS_LOGGED_IN, "");
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
        setContentView(R.layout.activity_main);

        initFragment();

    }

    private void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainFragmentContainer, new MainFragment());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.getSupportFragmentManager().popBackStackImmediate();
    }
}
