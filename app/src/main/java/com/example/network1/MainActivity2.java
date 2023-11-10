package com.example.network1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Photo> photoList = new ArrayList<>();
        photoAdapter = new PhotoAdapter(photoList);
        recyclerView.setAdapter(photoAdapter);

        //Call Api
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/photos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoService PhotoService=retrofit.create(PhotoService.class);
        Call<List<Photo>> call = PhotoService.getPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> photoList = null;
                if (response.isSuccessful()) {
                    photoList = response.body();
                    photoAdapter.setPhotoList(photoList);
                    Log.e("e", "Photo." + photoList);
                    photoAdapter.notifyDataSetChanged();
                } else {
                    Log.e("e", "Photo...." + photoList);
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });


    }
}