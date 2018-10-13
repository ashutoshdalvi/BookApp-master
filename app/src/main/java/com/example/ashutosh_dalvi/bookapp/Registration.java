package com.example.ashutosh_dalvi.bookapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.ashutosh_dalvi.bookapp.CurrentUser;


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
                final ProgressDialog progressDialog = new ProgressDialog(Registration.this);
                progressDialog.setMessage("Loading");
                try {

                    getdata();
                    progressDialog.show();
                    if (password.equals(cpassword)) {
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            CurrentUser.setFirembaseUser( auth.getCurrentUser().getUid());
                                             login();
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(Registration.this, "Account already exists", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Registration.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    progressDialog.dismiss();
                    Toast.makeText(Registration.this, "Please enter valid data(Exception)", Toast.LENGTH_SHORT).show();
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
    void login(){
        if(isNetworkAvailable()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String uid = firebaseUser.getUid();
                        fdatabase.child(uid).setValue(user);
                        Intent i = new Intent(Registration.this, Homepage.class);
                        startActivity(i);

                    }
                }
            });
        }else {
            Toast.makeText(Registration.this, "No Internet connection", Toast.LENGTH_SHORT).show();

        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
