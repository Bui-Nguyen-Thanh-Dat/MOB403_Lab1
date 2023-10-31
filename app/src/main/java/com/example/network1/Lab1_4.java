package com.example.network1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Lab1_4 extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button button;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab14);
        editText=findViewById(R.id.ed1);
        button=findViewById(R.id.btn1_4);
        tvResult=findViewById(R.id.tvResult);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner(tvResult,editText,this);
        String sleepTime= editText.getText().toString();
        asyncTaskRunner.execute(sleepTime);
    }

    public class AsyncTaskRunner extends AsyncTask<String, String , String>{
        private String resp;
        ProgressDialog dialog;
        TextView tvResult;
        EditText time;
        Context context;

        public AsyncTaskRunner(TextView tvResult, EditText time, Context context) {
            this.tvResult = tvResult;
            this.time = time;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog= ProgressDialog.show(context,"Progress dialog","Wait for "+time.getText().toString()+" seconds");

        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Sleeping...");
            try {
                int time =Integer.parseInt(strings[0])*1000;
                Thread.sleep(time);
                resp="Slept for "+ strings[0] + " seconds";

            }catch (Exception e){
                e.printStackTrace();
                resp=e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (dialog.isShowing()){dialog.dismiss();}
            tvResult.setText(s);
        }
    }
}