package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


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
                i = new Intent(this,Download.class);
                startActivity(i);
                break;
            case R.id.help:
                i = new Intent(this,Help.class);
                startActivity(i);
                break;
            case R.id.about_us:
                i = new Intent(this,Account.class);
                startActivity(i);
                break;
            case R.id.credits:
                i = new Intent(this,Credits.class);
                startActivity(i);
                break;
            case R.id.Home:
                i = new Intent(this,Homepage.class);
                startActivity(i);
                break;
            case R.id.feedback:
                i = new Intent(this,Feedback.class);
                startActivity(i);
                break;
        }
        return true;
    }

    void click(View view){
        Intent i = new Intent(this,Gridview.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer((GravityCompat.START));
        } else {
            super.onBackPressed();
        }
    }
    public void storageview(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
