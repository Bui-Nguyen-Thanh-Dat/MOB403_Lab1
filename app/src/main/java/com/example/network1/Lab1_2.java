package com.example.network1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Lab1_2 extends AppCompatActivity implements View.OnClickListener {
    private Button btnLoad;
    private ImageView imgAndroid;
    private TextView tvMessage;
    private ProgressDialog progressDialog;
    private Bitmap bitmap=null;
    private String url="https://porsche-vietnam.vn/wp-content/uploads/2023/02/982-718-c7-se-modelimage-sideshot-960x540.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab12);
        imgAndroid=findViewById(R.id.imgView1_2);
        btnLoad=findViewById(R.id.btnLoad);
        tvMessage=findViewById(R.id.tv2);

        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        progressDialog=ProgressDialog.show(Lab1_2.this,"","Downloading...");
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                bitmap=downloadBitmap(url);
                Message msg=messageHandler.obtainMessage();
                Bundle bundle=new Bundle();
                String threadMessage= "Image Downloaded";
                bundle.putString("message",threadMessage);
                msg.setData(bundle);
                messageHandler.sendMessage(msg);
            }
        };
        Thread athread=new Thread(runnable);
        athread.start();
    }

    private Bitmap downloadBitmap(String link){
        try {
            URL url=new URL(link);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    }

    private Handler messageHandler=new Handler(){
      public void handleMessage (Message msg){
          super.handleMessage(msg);
          Bundle bundle=msg.getData();
          String message= bundle.getString("message");
          tvMessage.setText(message);
          imgAndroid.setImageBitmap(bitmap);
          progressDialog.dismiss();
      }
    };
}