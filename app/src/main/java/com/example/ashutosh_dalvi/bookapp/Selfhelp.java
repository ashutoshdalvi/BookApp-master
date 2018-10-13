package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Selfhelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books/Self Help");
        final RecyclerView rv=findViewById(R.id.recyclerview_1);

        ArrayList<Book> selfhelp =new ArrayList<>();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //  String name=dataSnapshot.getKey();
                Book b1 =dataSnapshot.getValue(Book.class);
                ((Recyclerview_adapter)rv.getAdapter()).update(b1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Recyclerview_adapter adapter =new Recyclerview_adapter(rv,Selfhelp.this,selfhelp);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchbar, menu);
        return true;

    }
    public boolean search_click(MenuItem item){
        Intent i = new Intent(Selfhelp.this, Search.class);
        i.putExtra("category","Self Help");
        startActivity(i);
        //finish();
        return true;
    }
}
