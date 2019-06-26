package com.webakruti.volleyparsing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
    List<Hero> heroList = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;

    private Button buttonGotoMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        buttonGotoMainActivity = (Button)findViewById(R.id.buttonGotoMainActivity);
        buttonGotoMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,PojoListActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        loadHeroList();
    }

    private void setupRecycler(){
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(ListActivity.this,LinearLayoutManager.VERTICAL, false);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerAdapter = new RecyclerAdapter(ListActivity.this, heroList);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void loadHeroList() {

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("heroes");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Hero hero = new Hero(heroObject.getString("name"), heroObject.getString("imageurl"));

                                //adding the hero to herolist
                                heroList.add(hero);


                            }

                            //recyclerAdapter.notifyDataSetChanged();
                            setupRecycler();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
