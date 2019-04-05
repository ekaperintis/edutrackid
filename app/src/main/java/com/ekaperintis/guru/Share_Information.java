package com.ekaperintis.guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ekaperintis.guru.Adapter.InformationAdapter;
import com.ekaperintis.guru.Adapter.ProductAdapter;
import com.ekaperintis.guru.App.AppConfig;
import com.ekaperintis.guru.Model.Information;
import com.ekaperintis.guru.Model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Share_Information extends AppCompatActivity {

    List<Information> productList;

    RecyclerView recyclerView;

    private static final String HI = AppConfig.URL_TEACHER_LEARNING;
    private InformationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_information);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Share_Information.this, AddTeacherLearning.class);
                startActivity(i);
            }
        });


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        /*
        productList.add(
                new Information(
                        1,
                        "Pelatihan Blogger untuk guru Madrasah Aliyah Session 2",
                        "Cara pertama yang dapat guru lakukan agar murid dapat lebih cepat memahami materi adalah dengan menggunakan mind map.",
                        R.drawable.learning));

        productList.add(
                new Information(
                        1,
                        "Seminar Nasional sehari kurikulum K13 ",
                        "Kemerdekaan bangsa Indonesia dari penjajahan Jepang, Belanda, dan sekutu-sekutunya, tidak terlepas dari peran masyarakat Indonesia. ",

                        R.drawable.profile));

        productList.add(
                new Information(
                        1,
                        "Pelatihan Proktor untuk persiapan UNBK 2019",
                        "13.3 inch, Silver, 1.35 kg",
                        R.drawable.share_information));

        */

        //creating recyclerview adapter
        adapter = new InformationAdapter(this, productList);

        //setting adapter to recyclerview


        getData();

    }


    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("hasil >>", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);

                        productList.add(
                                new Information(
                                        1,
                                        ob.getString("title"),
                                        ob.getString("description"),
                                        ob.getString("imageurl")));


                        //List_Data ld=new List_Data(ob.getString("name"),ob.getString("imageurl"));
                        //list_data.add(ld);



                    }

                    recyclerView.setAdapter(adapter);
                    //recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
