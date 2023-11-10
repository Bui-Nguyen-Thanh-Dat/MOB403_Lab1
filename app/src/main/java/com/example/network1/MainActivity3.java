package com.example.network1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    List<Photo> photoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recyclerView=findViewById(R.id.recyclerview3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        asyncTask.execute("https://jsonplaceholder.typicode.com/photos");



    }

    AsyncTask<String,Void,String> asyncTask=new AsyncTask<String, Void, String>() {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");


                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();

                return stringBuilder.toString();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // Xử lý dữ liệu đã lấy được ở đây

            if (result != null) {
                // Ví dụ: Chuyển đổi dữ liệu JSON thành đối tượng Photo
                List<Photo> photoList = parseJsonToPhotoList(result);

            }
        }


    };

    public List<Photo> parseJsonToPhotoList(String json) {
        photoList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Đọc các thuộc tính từ jsonObject và tạo đối tượng Photo
                int albumId=jsonObject.getInt("albumId");
                int id = jsonObject.getInt("id");
                String url = jsonObject.getString("url");
                String title= jsonObject.getString("title");
                String thumbnailUrl=jsonObject.getString("thumbnailUrl");

                photoList.add(new Photo(albumId,id,title,url,thumbnailUrl));
                photoAdapter=new PhotoAdapter(photoList);
                recyclerView.setAdapter(photoAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return photoList;
    }

}