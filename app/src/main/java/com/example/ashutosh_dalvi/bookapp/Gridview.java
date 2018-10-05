package com.example.ashutosh_dalvi.bookapp;




import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Gridview extends AppCompatActivity {

    List<Book> mybooks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        Initiate();

        RecyclerView rv=findViewById(R.id.recyclerview_id);
        Recyclerview_adapter adapter =new Recyclerview_adapter(this,mybooks);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(adapter);



    }

    private void Initiate(){

        mybooks.add(new Book("A Feast For Crows","category","description",R.drawable.afeastforcrows));
        mybooks.add(new Book("A Storm Of Swords","category","description",R.drawable.astormofswords));
        mybooks.add(new Book("Animal Farm","category","description",R.drawable.animalfarm));
        mybooks.add(new Book("Diary Of A Wimpy Kid","category","description",R.drawable.diaryofawimpykid));
        mybooks.add(new Book("Famous Lat Words","category","description",R.drawable.famouslastwords));
        mybooks.add(new Book("Honour Among Thieves","category","description",R.drawable.honouramongthieves));
        mybooks.add(new Book("Journey into Europe","category","description",R.drawable.journeyintoeurope));
        mybooks.add(new Book("Revival","category","description",R.drawable.revival));
        mybooks.add(new Book("Sister of Mine","category","description",R.drawable.sisterofmine));
        mybooks.add(new Book("A Storm Of Swords","category","description",R.drawable.astormofswords));
        mybooks.add(new Book("Animal Farm","category","description",R.drawable.animalfarm));


    }
}

