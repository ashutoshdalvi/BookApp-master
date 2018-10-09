package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.ashutosh_dalvi.bookapp.CurrentUser;

public class Account extends AppCompatActivity {
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CurrentUser.firebaseUser = null;
                Intent i = new Intent(Account.this, Login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
