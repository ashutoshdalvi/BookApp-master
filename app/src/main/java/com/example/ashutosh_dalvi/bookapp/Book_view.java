package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Book_view extends AppCompatActivity {
    private PDFView pdf;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);
        pdf = (PDFView)findViewById(R.id.pdfview);
        Intent i = getIntent();
        url = i.getExtras().getString("url").trim();
        new Retrieve().execute(url);

    }
     class Retrieve extends AsyncTask<String,Void ,InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream=null;
            try{
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection  = (HttpURLConnection)url.openConnection();
                if(urlConnection.getResponseCode()==200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }catch(IOException e){
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdf.fromStream(inputStream).load();
        }
    }
}
