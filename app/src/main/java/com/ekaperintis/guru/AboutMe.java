package com.ekaperintis.guru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ekaperintis.guru.Helper.SQLiteHandler;

import java.util.HashMap;

public class AboutMe extends AppCompatActivity {

    private SQLiteHandler db;

    private TextView textViewName;
    private TextView textViewEmail;
    private TextView textViewKelamin;
    private TextView textViewAlamat;
    private TextView textViewProvinsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_me);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");
        String nipd = user.get("nipd");

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);

        textViewName.setText(name);
        textViewEmail.setText(email);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
