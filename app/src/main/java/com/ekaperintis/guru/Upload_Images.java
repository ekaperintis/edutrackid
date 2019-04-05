package com.ekaperintis.guru;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ekaperintis.guru.App.AppConfig;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Upload_Images extends AppCompatActivity {
    private static final String HI = AppConfig.URL_UPLOAD_TEACHER;
    private ImageView img;
    private Button selectImg, uploadImg;
    private Uri filepath;
    Bitmap bitmap;
    final int IMAGE_REQUEST_CODE = 999;

    private ProgressDialog pDialog;

    private String judul, keterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_image);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        img = (ImageView) findViewById(R.id.imagview);
        selectImg = (Button) findViewById(R.id.select_img);
        uploadImg = (Button) findViewById(R.id.upload_img);

        selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(Upload_Images.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},IMAGE_REQUEST_CODE);
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                judul= null;
                keterangan =null;
            } else {
                judul= extras.getString("judul");
                keterangan = extras.getString("keterangan");
                Toast.makeText(getApplicationContext(),judul,Toast.LENGTH_LONG).show();
            }
        } else {
            judul= (String) savedInstanceState.getSerializable("judul");
            keterangan = (String) savedInstanceState.getSerializable("keterangan");
        }

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("Uploading file ...");
                showDialog();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, HI, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Upload_Images.this, response.toString(),
                                Toast.LENGTH_LONG).show();
                        finish();
                        hideDialog();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parms=new HashMap<String, String>();
                        String imgdata=imgToString(bitmap);
                        parms.put("imageurl",imgdata);
                        parms.put("judul", judul);
                        parms.put("keterangan", keterangan);
                        return parms;

                    }
                };
                RequestQueue rq= Volley.newRequestQueue(Upload_Images.this);
                rq.add(stringRequest);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        if (requestCode==IMAGE_REQUEST_CODE){
            if (grantResults.length>0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(new Intent(Intent.ACTION_PICK));
                intent.setType("image/*");

                startActivityForResult(Intent.createChooser(intent,"select image"),IMAGE_REQUEST_CODE);

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==IMAGE_REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String imgToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes=byteArrayOutputStream.toByteArray();
        String encodeimg= Base64.encodeToString(imgbytes,Base64.DEFAULT);
        return encodeimg;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
