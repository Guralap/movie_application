package com.example.a1691677.movie_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class movie_detail extends AppCompatActivity implements CallBackMe {
    TextView t1;
    ImageView i1;
    TextView t2;
    TextView t3;
    int movieID;



    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        t1 = findViewById(R.id.textView2);
        i1 = findViewById(R.id.imageView2);
        t2 = findViewById(R.id.textView3);
        t3 = findViewById(R.id.textView4);
        scroll=findViewById(R.id.scroll);



        Intent myIntent1 = getIntent();
        String url = myIntent1.getStringExtra("url");

        JsonRetriever.RetrieveFromURL(this, url, this);

    }


    public void CallThis(String jsonText) {

        try {
            //    JSONObject json = new JSONObject(jsonText);
            String imgUrl = "https://image.tmdb.org/t/p/w500";


            JSONObject json = new JSONObject(jsonText);
            t1.setText(json.getString("original_title"));
            t2.setText(json.getString("overview"));
            t3.setText(json.getString("popularity"));
            movieID=json.getInt("id");
            String complete = imgUrl + json.getString("poster_path");

            new DownloadImageTask(i1)
                    .execute(complete);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
public void getClick(View view){
    Intent myintent5 = new Intent(this, cast.class);
    myintent5.putExtra("id",movieID);
    startActivity(myintent5);
}

    }

