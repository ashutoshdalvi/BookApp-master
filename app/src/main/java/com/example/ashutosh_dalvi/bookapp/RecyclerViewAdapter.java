package com.example.ashutosh_dalvi.bookapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//import com.bumptech.glide.Glide;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mNames= new ArrayList<>();
    private ArrayList<Integer > thumbnail = new ArrayList<>();

    private List<Book> mbook;
    private Context mContext ;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mNames, ArrayList<Integer> thumbnail) {
        this.mNames = mNames;
        this.thumbnail = thumbnail;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(mNames.get(position));
        holder.name.setBackgroundResource(thumbnail.get(position));



        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (mNames.get(position)){

                    case "MARATHI":{
                        Intent intent = new Intent(mContext,Marathi.class);
                        mContext.startActivity(intent);
                        break;
                    }
                    case "FICTION":{
                        Intent intent1 = new Intent(mContext,Fiction.class);
                        mContext.startActivity(intent1);
                        break;
                    }
                    case "THRILLER":{
                        Intent intent2 = new Intent(mContext,Thriller.class);
                        mContext.startActivity(intent2);
                        break;
                    }
                   case "ROMANCE":{
                        Intent intent3 = new Intent(mContext,Romance.class);
                        mContext.startActivity(intent3);
                        break;
                    }
                    case "SELF HELP":{
                        Intent intent5 = new Intent(mContext,Selfhelp.class);
                        mContext.startActivity(intent5);
                        break;
                    }
                    case "TECHNICAL":{
                        Intent intent4 = new Intent(mContext,Technical.class);
                        mContext.startActivity(intent4);
                        break;
                    }

                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return thumbnail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       // ImageView image ;
        TextView name;
       // Layout

        public ViewHolder(View itemView) {
            super(itemView);
          //  image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.cat_name);
        }
    }


}
