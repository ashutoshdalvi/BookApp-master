package com.example.ashutosh_dalvi.bookapp;




import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Recyclerview_adapter extends RecyclerView.Adapter<Recyclerview_adapter.MyViewHolder>{


    private Context mcontext;
    private List<Book> mbook;

    public Recyclerview_adapter(Context mcontext, List<Book> mbook) {
        this.mcontext = mcontext;
        this.mbook = mbook;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater= LayoutInflater.from(mcontext);
        view = mInflater.inflate(R.layout.cardview_listitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.bookname.setText(mbook.get(position).getName());
        holder.bookimage.setImageResource(mbook.get(position).getThumbnail());
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mbook.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookname;
        ImageView bookimage;
        //CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            bookname=(TextView)itemView.findViewById(R.id.book_name_id);
            bookimage=(ImageView)itemView.findViewById(R.id.image_view_id);
           // cardView=(CardView)itemView.findViewById(R.id.cardview_id);
        }



    }

}

