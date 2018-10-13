package com.example.ashutosh_dalvi.bookapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.ashutosh_dalvi.bookapp.CurrentUser;
import com.google.firebase.database.ValueEventListener;

public class Feedback extends AppCompatActivity {
    private Button btn;
    private String uid,name;
    private  User user;
    private EditText etfeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        uid =CurrentUser.getFirembaseUser();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("Users").child(uid);
        final DatabaseReference ref2 = database.getReference("Feedback");
        btn = (Button)findViewById(R.id.submit);
        etfeedback = (EditText)findViewById(R.id.feedback);

        ref.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String key =dataSnapshot.getKey();
                user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref2.child(user.getName()).push().setValue(etfeedback.getText().toString());
                Toast.makeText(Feedback.this, "Submitted", Toast.LENGTH_SHORT).show();
                etfeedback.setText("");

            }
        });
    }
}
