package com.ekaperintis.guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Teacher_Learning_Detail extends AppCompatActivity {

    private TextView nametxt;
    private ImageView fullimg;
    private TextView description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_learning_detail);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        nametxt=(TextView)findViewById(R.id.title);
        description=(TextView)findViewById(R.id.description);
        fullimg=(ImageView)findViewById(R.id.imageurl);

        Intent i=getIntent();
        String name=i.getStringExtra("title");
        String imageurl=i.getStringExtra("imageurl");
        String desc = i.getStringExtra("description");

        nametxt.setText(name);
        description.setText(desc);

        Picasso.with(this)
                .load(imageurl)
                .into(fullimg);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
