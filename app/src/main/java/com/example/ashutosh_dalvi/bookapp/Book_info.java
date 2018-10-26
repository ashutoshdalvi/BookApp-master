package com.example.ashutosh_dalvi.bookapp;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.media.session.IMediaControllerCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URI;
import com.example.ashutosh_dalvi.bookapp.CurrentUser;


public class Book_info extends AppCompatActivity {
    private String book_name,book_url,image_url,desc,uid;
    private TextView bookname,description;
    private ImageView bookimg;
    private FloatingActionButton read,download;
    private User user;
    private DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon( R.drawable.backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        uid =CurrentUser.getFirembaseUser();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref2 = database.getReference("Downloaded Books");
        final DatabaseReference ref3 = database.getReference("Books Read");
        final DatabaseReference ref = database.getReference("Users").child(uid);

        Intent i = getIntent();
        book_name = i.getExtras().getString("book_name");
        book_url = i.getExtras().getString("book_url");
        desc = i.getExtras().getString("description");
        image_url = i.getExtras().getString("img_url");

        bookname = (TextView) findViewById(R.id.book_name_id);
        description=(TextView) findViewById(R.id.book_desc_id);
        bookimg = (ImageView)findViewById(R.id.book_img_id);
        read = (FloatingActionButton)findViewById(R.id.read);
        download= (FloatingActionButton)findViewById(R.id.download);

        description.setText(desc);
        bookname.setText(book_name);
        Picasso.get().load(image_url).into(bookimg);

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
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission ","You have permission");
            }else {
                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(Book_info.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent=new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(book_url));
                startActivity(intent);*/
                if(isNetworkAvailable()) {
                    downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri uri = Uri.parse(book_url);
                    DownloadManager.Request request = new DownloadManager.Request(uri);

                    request.setTitle(book_name + ".pdf");
                    Toast.makeText(Book_info.this, "Downloading...", Toast.LENGTH_SHORT).show();

                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setVisibleInDownloadsUi(true);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, book_name + ".pdf");
                    Long reference = downloadManager.enqueue(request);
                    ref2.child(user.getName()).child(book_name).setValue(book_name);
                }else {
                    Toast.makeText(Book_info.this, "No Internet connection", Toast.LENGTH_SHORT).show();

                }

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref3.child(user.getName()).child(book_name).setValue(book_name);
                Intent i = new Intent(Book_info.this,Book_view.class);
                i.putExtra("url",book_url);
                startActivity(i);
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
