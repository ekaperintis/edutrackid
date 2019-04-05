package com.ekaperintis.guru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Loading extends Activity {
    private static int splashInterval = 2000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Loading.this, Login.class);
                startActivity(i);
                finish();
            }


        }, splashInterval);


    }
}

/*
http://emka.web.id/tutorial/tutorial-php/2016/tutorial-php-membuat-push-notification-realtime-dengan-onesignal-api/
 */