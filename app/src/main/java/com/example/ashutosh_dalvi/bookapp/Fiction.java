package com.example.ashutosh_dalvi.bookapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Fiction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books/Fiction");
        final RecyclerView rv=findViewById(R.id.recyclerview_marathi);

        ArrayList<Book> fiction =new ArrayList<>();

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

        Recyclerview_adapter adapter =new Recyclerview_adapter(rv,Fiction.this,fiction);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(adapter);
    }
}
