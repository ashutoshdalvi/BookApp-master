package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void onClick(View view) {
        Intent i = new Intent(this, Registration.class);
        startActivity(i);

    }
    public void cardclick(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
    }
