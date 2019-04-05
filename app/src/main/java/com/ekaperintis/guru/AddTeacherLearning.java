package com.ekaperintis.guru;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTeacherLearning extends AppCompatActivity {

    private Button btnnext;
    EditText txt_judul, txt_keterangan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_teacher_learning);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        txt_judul = (EditText) findViewById(R.id.txt_judul);
        txt_keterangan = (EditText) findViewById(R.id.txt_keterangan);

        btnnext = (Button) findViewById(R.id.btn_next);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AddTeacherLearning.this, Upload_Images.class);
                i.putExtra("judul", txt_judul.getText().toString());
                i.putExtra("keterangan", txt_keterangan.getText().toString());
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
