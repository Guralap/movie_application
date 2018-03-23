package com.example.a1691677.movie_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class cast extends AppCompatActivity implements CallBackMe {
    ListView cast;
String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        cast = findViewById(R.id.cast);

        Intent myIntent5 = getIntent();
        int movieID = myIntent5.getIntExtra("id",-1);

        String url = "https://api.themoviedb.org/3/movie/"+movieID+"/credits?api_key=f829eca8d877e9c53d4729499a3e79bb";


        //This will retrieve the string json from the URL requested
        JsonRetriever.RetrieveFromURL(this, url, this);
        //First Param for Context, Last Param for Callback Function
        //First param is required for the library
        //Third param, allows to use any class that implements CallB

    }


    @Override
    public void CallThis(String jsonText) {

        //Parse the Json here
        //Good examples: https://stackoverflow.com/questions/8091051/how-to-parse-json-string-in-android

        try {

            JSONObject jsonfirst = new JSONObject(jsonText);
            JSONArray aray = jsonfirst.getJSONArray("cast");
            //    JSONObject json = new JSONObject(jsonText);

            String imgUrl = "https://image.tmdb.org/t/p/w500";

            ArrayList<Feed> myFeeds = new ArrayList<>();


            for (int i = 0; i < aray.length(); i++) {

                JSONObject json = aray.getJSONObject(i);
                String url = "https://api.themoviedb.org/3/movie/" + json.getString("id") + "?api_key=f829eca8d877e9c53d4729499a3e79bb";
                Feed oneMovie = new Feed(json.getString("name"), imgUrl + json.getString("profile_path"), url);
                myFeeds.add(oneMovie);

            }


            //Loop is done
            ListView feedListView = findViewById(R.id.cast);
            CustomFeedAdapter feedAdapter = new CustomFeedAdapter(this, myFeeds);
            feedListView.setAdapter(feedAdapter);   //Set the data for this ListView
            feedListView.setOnItemClickListener(    //Creating these on the fly.
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Feed myFeed = (Feed) parent.getItemAtPosition(position);
                            gotClicked(myFeed);
                        }
                    }
            );

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void gotClicked(Feed feed) {

    }
}
