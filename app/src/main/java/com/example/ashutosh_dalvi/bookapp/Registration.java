package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {

    private DatabaseReference fdatabase;
    private EditText etname, etmail_id, etpassword, etcpassword, etcontact;
    private CardView button;
    private String name,email, password,contact,cpassword;
    private User user = new User();
    FirebaseDatabase ref;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etname = (EditText) findViewById(R.id.editText1);
        etmail_id = (EditText) findViewById(R.id.editText2);
        etcontact = (EditText) findViewById(R.id.editText3);
        etpassword = (EditText) findViewById(R.id.editText4);
        etcpassword = (EditText) findViewById(R.id.editText5);
        button = (CardView) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getdata();
                    if (password.equals(cpassword)) {
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            fdatabase.push().setValue(user);
                                            Intent i = new Intent(Registration.this, MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        } else {
                                            Toast.makeText(Registration.this, "Account already exists", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(Registration.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(Registration.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance();
        fdatabase = ref.getReference("Users");

    }

    public void getdata() {
        email = etmail_id.getText().toString();
        password = etpassword.getText().toString();
        name = etname.getText().toString();
        contact = etcontact.getText().toString();
        cpassword= etcpassword.getText().toString();
        user.setName(name);
        user.setMail_id(email);
        user.setPassword(password);
        user.setContact(contact);
    }
}
