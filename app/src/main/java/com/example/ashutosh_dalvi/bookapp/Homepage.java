package com.example.ashutosh_dalvi.bookapp;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    ArrayList<String> mNames= new ArrayList<>();
    ArrayList<Integer > mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        recyclerview1();
        recyclerview2();
        recyclerview3();

        if(!isNetworkAvailable())
            Toast.makeText(Homepage.this, "No Internet connection", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.account:
                i = new Intent(this,Account.class);
                startActivity(i);
                break;
            case R.id.download:
                i = new Intent();
                i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(i);
                break;

            case R.id.about_us:
                i = new Intent(this,Aboutus.class);
                startActivity(i);
                break;
            case R.id.credits:
                i = new Intent(this,Credits.class);
                startActivity(i);
                break;
            case R.id.feedback:
                i = new Intent(this,Feedback.class);
                startActivity(i);
                break;
        }
        drawer.closeDrawer((GravityCompat.START));
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer((GravityCompat.START));
        } else {
            finishAffinity();
        }
    }

    private void initImageBitmaps(){
        mNames.add("Marathi");
        mImageUrls.add(R.drawable.marathi);

        mNames.add("TECHNICAL");
        mImageUrls.add(R.drawable.technical);

        mNames.add("FICTION");
        mImageUrls.add(R.drawable.fiction);

        mNames.add("ROMANCE");
        mImageUrls.add(R.drawable.romance);

        mNames.add("THRILLER");
        mImageUrls.add(R.drawable.thriller1);

        mNames.add("SELF HELP");
        mImageUrls.add(R.drawable.selfhelp);
    }

    public void recyclerview1(){
        RecyclerView recyclerView =findViewById(R.id.recyclerview_id1);
        initImageBitmaps();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter =new RecyclerViewAdapter(this,mNames,mImageUrls);
        recyclerView.setAdapter(adapter);

    }

    private void recyclerview2() {

        final RecyclerView recyclerView =findViewById(R.id.recyclerview_id2);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books").child("Trending");
        ArrayList<Book> trendingbooks =new ArrayList<>();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //  String name=dataSnapshot.getKey();
                Book b1 =dataSnapshot.getValue(Book.class);
                ((Recyclerview_adapter)recyclerView.getAdapter()).update(b1);
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        Recyclerview_adapter adapter =new Recyclerview_adapter(recyclerView,Homepage.this,trendingbooks);
        recyclerView.setAdapter(adapter);
    }

    private void recyclerview3() {
        final RecyclerView recyclerView =findViewById(R.id.recyclerview_id3);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Books/New Releases");
        ArrayList<Book> newreleases =new ArrayList<>();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //  String name=dataSnapshot.getKey();
                Book b1 =dataSnapshot.getValue(Book.class);
                ((Recyclerview_adapter)recyclerView.getAdapter()).update(b1);
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        Recyclerview_adapter adapter =new Recyclerview_adapter(recyclerView,Homepage.this,newreleases);
        recyclerView.setAdapter(adapter);

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchbar, menu);
        return true;

    }
   public boolean search_click(MenuItem item){
       Intent i = new Intent(Homepage.this, Search.class);
       i.putExtra("category","All Books");
       startActivity(i);
       return true;
   }

}

