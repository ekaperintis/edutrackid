package com.ekaperintis.guru;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ekaperintis.guru.Helper.SQLiteHandler;
import com.ekaperintis.guru.Helper.SessionManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.HashMap;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class Dashboard extends Activity {

    private LinearLayout btn_learning;
    private LinearLayout btn_share;
    private LinearLayout btn_about;

    private SQLiteHandler db;
    private SessionManager session;

    private TextView txtWelcome;
    private TextView txtName;
    private TextView txtEmail;
    private TextView txt_versi;

    private ImageView img_home;
    Transformation transformation;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_guru);

        //txtWelcome = (TextView) findViewById(R.id.welcome);
        txtName = (TextView) findViewById(R.id.txt_nama);
        txtEmail = (TextView) findViewById(R.id.txt_email);
        //txt_versi = (TextView) findViewById(R.id.txt_versi);


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());
        //version = session.getKeyVersi();

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");
        String nipd = user.get("nipd");

        //txtWelcome.setText("Welcome, " + name);
        txtName.setText("(" + name + ")");
        txtEmail.setText(email);
        //str_nipd = nipd;

        btn_learning = (LinearLayout) findViewById(R.id.btn_learning);
        btn_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        ListTeacherLearning.class);
                startActivity(i);
            }

        });

        btn_share = (LinearLayout) findViewById(R.id.btn_share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Share_Information.class);
                startActivity(i);
            }

        });

        btn_about = (LinearLayout) findViewById(R.id.btn_about);
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        AboutMe.class);
                startActivity(i);
            }

        });

        img_home = (ImageView) findViewById(R.id.img_home);
        /*
        Picasso.with(this).load(R.drawable.noimagefound)
                .transform(transformation)
                .transform(new CropCircleTransformation())
                .into(img_home);
        */

        Picasso.with(this)
                .load(R.drawable.noimagefound)
                .resize(100, 100)
                .transform(new CropCircleTransformation())
                .into(img_home);

    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();
        // Launching the login activity
        Intent intent = new Intent(Dashboard.this, Login.class);
        startActivity(intent);
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("KELUAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                })
                .setNegativeButton("LOGOUT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        logoutUser();
                        Dashboard.this.finish();
                        //finishAffinity();
                        System.exit(0);
                    }
                })
                .show();

    }
}
