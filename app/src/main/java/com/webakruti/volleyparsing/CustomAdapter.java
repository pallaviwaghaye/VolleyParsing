package com.webakruti.volleyparsing;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by DELL on 2/9/2019.
 */

public class CustomAdapter extends ArrayAdapter<Hero> {

    //the hero list that will be displayed
    private List<Hero> heroList;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public CustomAdapter(List<Hero> heroList, Context mCtx) {
        super(mCtx, R.layout.list_row, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_row, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        ImageView imageViewImageUrl = listViewItem.findViewById(R.id.imageViewImageUrl);

        //Getting the hero for the specified position
        Hero hero = heroList.get(position);

        //setting hero values to textviews
        textViewName.setText(hero.getName());
        Picasso.with(mCtx)
                .load(hero.getImageUrl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageViewImageUrl);
        //imageViewImageUrl.setText(hero.getImageUrl());

        //returning the listitem
        return listViewItem;
    }
}
