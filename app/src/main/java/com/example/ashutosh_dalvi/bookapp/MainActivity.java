package com.example.ashutosh_dalvi.bookapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    EditText etname,etdesc,etcategory;
    private String name,name2,name1,description,category,book_url,image_url;
    Button btn;
    Sample sample = new Sample();
    private StorageReference storage,temp;
    private DatabaseReference ref1,ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = (EditText) findViewById(R.id.editText);
        etdesc = (EditText)findViewById(R.id.editText7);
        etcategory = (EditText)findViewById(R.id.editText8);
        btn = (Button) findViewById(R.id.button3);

      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              getdata();
              ref2 = FirebaseDatabase.getInstance().getReference("Books").child(category);
                name2 = name +".pdf";
                name1 = name + ".jpg";
                book_url = storage.child(name).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Toast.makeText(MainActivity.this, "  exists", Toast.LENGTH_SHORT).show();
                    }
                }).toString();
                image_url = temp.child(name).getDownloadUrl().toString();
                sample.setBook_url(book_url);
                sample.setImage_url(image_url);
                ref1.child(name).setValue(sample);
                ref2.child(name).setValue(sample);

          }
      });
      ref1 = FirebaseDatabase.getInstance().getReference("Books").child("All Books");
        storage = FirebaseStorage.getInstance().getReference("Books/");
        temp = FirebaseStorage.getInstance().getReference("Images/");
    }
    public void getdata() {
        name = etname.getText().toString().trim();
        description = etdesc.getText().toString();
        category = etcategory.getText().toString();
        sample.setName(name);
        sample.setDescription(description);

    }

}
