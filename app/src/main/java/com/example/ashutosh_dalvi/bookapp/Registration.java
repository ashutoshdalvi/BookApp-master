package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

public class Registration extends AppCompatActivity {

    //private DatabaseReference fdatabase;
    private EditText name,mail_id,password,cpassword,contact;
    private Button button;
    private User user= new User();
    //FirebaseDatabase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = (EditText)findViewById(R.id.editText1);
        mail_id = (EditText)findViewById(R.id.editText2);
        contact = (EditText)findViewById(R.id.editText3);
        password = (EditText)findViewById(R.id.editText4);
        cpassword = (EditText)findViewById(R.id.editText5);


        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Registration.this,MainActivity.class);
                startActivity(i);

            }
        });*/

        //ref = FirebaseDatabase.getInstance();
        //fdatabase = ref.getReference();

    }
    public void getdata(){
        user.setName(name.getText().toString());
        user.setMail_id(mail_id.getText().toString());
        user.setPassword(password.getText().toString());
        user.setContact(contact.getText().toString());
    }
    /*public void signinconn(View view){
        //fdatabase.addValueEventListener(new ValueEventListener() {
        //    @Override
         //   public void onDataChange(DataSnapshot dataSnapshot) {
                getdata();
                //fdatabase.push().setValue(user);
               Toast.makeText(Registration.this, "Data Inserted...", Toast.LENGTH_SHORT).show();

            }*/

           // @Override
          //  public void onCancelled(DatabaseError databaseError) {

           // }
        //});
    }

//}
