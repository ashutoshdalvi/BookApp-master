package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ashutosh_dalvi.bookapp.CurrentUser;
import com.example.ashutosh_dalvi.bookapp.CurrentUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends AppCompatActivity {
    Button btn ;
    String uid,s;
    User user;
    TextView name,email,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        uid =CurrentUser.getFirembaseUser();
        btn = (Button)findViewById(R.id.edit);
        name = (TextView)findViewById(R.id.user_name);
        email = (TextView)findViewById(R.id.email_id);
        contact = (TextView)findViewById(R.id.mobile_no);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users").child(uid);

        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key =dataSnapshot.getKey();
                user = dataSnapshot.getValue(User.class);
                name.setText(user.getName());
                email.setText(user.getMail_id());
                contact.setText(user.getContact());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CurrentUser.setFirembaseUser (null);
              /*  Intent i = new Intent(Account.this, Login.class);
                startActivity(i);
                finish();*/
            }
        });
    }
}
