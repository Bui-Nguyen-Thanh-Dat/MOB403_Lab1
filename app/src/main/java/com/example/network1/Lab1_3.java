package com.example.network1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lab1_3 extends AppCompatActivity implements View.OnClickListener,Listener{
    private Button btnLoad;
    private ImageView imgAndroid;
    private TextView tvMessage;

    public static final String image_url="https://porsche-vietnam.vn/wp-content/uploads/2023/02/982-718-c7-se-modelimage-sideshot-960x540.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab13);

        tvMessage=findViewById(R.id.tv1_3);
        imgAndroid=findViewById(R.id.imgView1_3);
        btnLoad=(Button) findViewById(R.id.btnLoad1_3);

        btnLoad.setOnClickListener(this);

    }


    @Override
    public void onClick(@NonNull View view) {
        new LoadImageTask(this,this).execute(image_url);
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
         imgAndroid.setImageBitmap(bitmap);
         tvMessage.setText("Image dowloaded");
    }

    @Override
    public void onError() {
         tvMessage.setText("Error download image");
    }
}