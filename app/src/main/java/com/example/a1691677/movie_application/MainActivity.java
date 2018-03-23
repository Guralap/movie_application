package com.example.a1691677.movie_application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements CallBackMe {

Button searchMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchMovie=findViewById(R.id.searchmovie);


        String url = "https://api.themoviedb.org/3/movie/popular?api_key=f829eca8d877e9c53d4729499a3e79bb";


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
            JSONArray aray = jsonfirst.getJSONArray("results");
            //    JSONObject json = new JSONObject(jsonText);

            String imgUrl = "https://image.tmdb.org/t/p/w500";

            ArrayList<Feed> myFeeds = new ArrayList<>();


            for (int i = 0; i < aray.length(); i++) {

                JSONObject json = aray.getJSONObject(i);
                String url = "https://api.themoviedb.org/3/movie/" + json.getString("id") + "?api_key=f829eca8d877e9c53d4729499a3e79bb&language=en-US";
                Feed oneMovie = new Feed(json.getString("title"), imgUrl + json.getString("poster_path"), url);
                myFeeds.add(oneMovie);

            }


            //Loop is done
            ListView feedListView = findViewById(R.id.listView);
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
        Intent myintent1 = new Intent(this, movie_detail.class);
        myintent1.putExtra("url", feed.url);//-
        startActivity(myintent1);

    }

    public void Clicked(View view) {
        Intent myintent3 = new Intent(this, search.class);
        startActivity(myintent3);

    }
}