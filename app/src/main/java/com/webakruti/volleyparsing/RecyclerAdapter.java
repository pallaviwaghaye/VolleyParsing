package com.webakruti.volleyparsing;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 2/11/2019.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Activity context;
    List<Hero> list;
    int size;

    public RecyclerAdapter(Activity context, List<Hero> list) {
        this.context = context;
        this.size = size;
        this.list = list;

    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_recycler, viewGroup, false);
        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapter.ViewHolder viewHolder, final int position) {
        final Hero hero = list.get(position);

        viewHolder.textViewName.setText(hero.getName());

       /* Toast.makeText(context,"CategoryName = "+ category.getName(),Toast.LENGTH_LONG).show();
        Toast.makeText(context,"CategoryName : "+ category.getName(),Toast.LENGTH_LONG).show();
*/
        Picasso.with(context)
                .load(hero.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(viewHolder.imageViewImageUrl);

        /*viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubcategoryActivity.class);
                intent.putExtra("KamgarCategory", (Serializable) category);
                context.startActivity(intent);
            }
        });*/

       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubcategoryActivity.class);
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private ImageView imageViewImageUrl;
        //private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            //cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewName = (TextView)itemView.findViewById(R.id.textViewName);
            imageViewImageUrl = (ImageView)itemView.findViewById(R.id.imageViewImageUrl);


        }
    }
}
