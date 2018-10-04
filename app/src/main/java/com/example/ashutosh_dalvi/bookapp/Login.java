package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PrivateKey;

public class Login extends AppCompatActivity {
    private EditText etmail,etpassword;
    private String email,password;
    private FirebaseAuth auth;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etmail = (EditText)findViewById(R.id.email);
        etpassword= (EditText)findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();

    }

    public void onClick(View view) {
        Intent i = new Intent(this, Registration.class);
        startActivity(i);

    }
    public void cardclick(View view){
       try {
            email=etmail.getText().toString();
            password=etpassword.getText().toString();
            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                ref = FirebaseDatabase.getInstance().getReference(email);
                                Intent i = new Intent(Login.this, Homepage.class);
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(Login.this, " Invalid data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }catch(Exception e){
            Toast.makeText(Login.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
        }
    }
}
