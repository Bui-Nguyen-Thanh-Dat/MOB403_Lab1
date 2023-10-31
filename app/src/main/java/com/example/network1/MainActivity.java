package com.example.network1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnLoad;
    private ImageView imgAndroid;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoad =(Button) findViewById(R.id.button);
        imgAndroid= (ImageView)findViewById(R.id.imageView);
        tvMessage=(TextView)findViewById(R.id.textView2);

        btnLoad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final Thread myThread=new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap =loadImageFromNetwork("https://porsche-vietnam.vn/wp-content/uploads/2023/02/982-718-c7-se-modelimage-sideshot-960x540.png");
                imgAndroid.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMessage.setText("Image downloaded");
                        imgAndroid.setImageBitmap(bitmap);
                        Log.e("e","Bit map "+bitmap);
                    }
                });
            }
        });
        myThread.start();
    }

    private Bitmap loadImageFromNetwork(String link){
        URL url;
        Bitmap bmp=null;

        try {
            url=new URL(link);
            bmp=BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        return bmp;
    }



}
