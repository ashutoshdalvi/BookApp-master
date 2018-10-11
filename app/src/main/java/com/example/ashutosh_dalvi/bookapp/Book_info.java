package com.example.ashutosh_dalvi.bookapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.squareup.picasso.Picasso;

public class Book_info extends AppCompatActivity {
    private String book_name,book_url,image_url,desc;
    private TextView bookname,description;
    private ImageView bookimg;
    private FloatingActionButton read,download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent i =getIntent();

        book_name = i.getExtras().getString("book_name");
        book_url = i.getExtras().getString("book_url");
        desc = i.getExtras().getString("description");
        image_url = i.getExtras().getString("img_url");

        bookname = (TextView) findViewById(R.id.book_name_id);
        description=(TextView) findViewById(R.id.book_desc_id);
        bookimg = (ImageView)findViewById(R.id.book_img_id);
        //read = (FloatingActionButton)findViewById(R.id.read);
        download= (FloatingActionButton)findViewById(R.id.download);

        description.setText(desc);
        bookname.setText(book_name);
        Picasso.get().load(image_url).into(bookimg);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(book_url));
                startActivity(intent);

            }
        });

     /*   read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Book_info.this,Book_view.class);
                i.putExtra("url",book_url);
                startActivity(i);
            }
        });
*/

    }

}
