package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ashutosh_dalvi.bookapp.CurrentUser;

public class FlashScreen extends AppCompatActivity {
    private final static int time = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(CurrentUser.getFirembaseUser()!=null){
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
