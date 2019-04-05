package com.ekaperintis.guru;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ekaperintis.guru.Adapter.ProductAdapter;
import com.ekaperintis.guru.Model.Product;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.ekaperintis.guru.App.AppConfig;

public class ListTeacherLearning extends  AppCompatActivity {

    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;

    private static final String HI = AppConfig.URL_TEACHER_LEARNING;
    private RecyclerView rv;
    //private List<List_Data>list_data;
    //private MyAdapter adapter;

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_learning);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListTeacherLearning.this, AddTeacherLearning.class);
                startActivity(i);
            }
        });



        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();


        //adding some items to our list
    /*
        productList.add(
                new Product(
                        1,
                        "5 Cara Mengajar Agar Murid Cepat Paham",
                        "Cara pertama yang dapat guru lakukan agar murid dapat lebih cepat memahami materi adalah dengan menggunakan mind map.",
                        4.3,
                        60000,
                        R.drawable.learning));

        productList.add(
                new Product(
                        1,
                        "Mengenal Ide dan Gagasan Pendidikan dari Para Tokoh Nasional",
                        "Kemerdekaan bangsa Indonesia dari penjajahan Jepang, Belanda, dan sekutu-sekutunya, tidak terlepas dari peran masyarakat Indonesia. ",
                        4.3,
                        60000,
                        R.drawable.profile));

        productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.share_information));

*/
        //creating recyclerview adapter
        adapter = new ProductAdapter(this, productList);

        //setting adapter to recyclerview
        //recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        productList.clear();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("hasil >>", response);

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray  array=jsonObject.getJSONArray("data");
                    for (int i=0; i<array.length(); i++){
                        JSONObject ob=array.getJSONObject(i);

                        productList.add(
                                new Product(
                                        1,
                                        ob.getString("title"),
                                        ob.getString("description"),
                                        4.3,
                                        60000,
                                        ob.getString("imageurl")));

                        //List_Data ld=new List_Data(ob.getString("name"),ob.getString("imageurl"));
                        //list_data.add(ld);
                    }
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }else if (id == R.id.actRefresh) {
            getData();
        }
        return super.onOptionsItemSelected(item);
    }
}

/*
https://uniqueandrocode.com/click-listener-in-recyclerview-and-json-parsing-using-volley/
https://uniqueandrocode.com/json-parsing-with-recyclerview-in-android-using-volley/
https://codetanwir.blogspot.com/2017/07/menampilkan-data-menggunakan_7.html
 */