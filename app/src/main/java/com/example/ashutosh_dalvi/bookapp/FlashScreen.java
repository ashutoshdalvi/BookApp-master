package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ashutosh_dalvi.bookapp.CurrentUser;

public class FlashScreen extends AppCompatActivity {
    private final static int time = 1200;
    private SharedPreferences sharedPreferences;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionbar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashscreen);
        sharedPreferences = getSharedPreferences("user_uid",MODE_PRIVATE);
        uid=sharedPreferences.getString("uid",null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(uid!=null){
                    CurrentUser.setFirembaseUser(uid);
                    Intent i = new Intent(FlashScreen.this, Homepage.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(FlashScreen.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }
        },time);

    }

}
