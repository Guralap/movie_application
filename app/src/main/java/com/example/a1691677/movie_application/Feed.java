package com.example.a1691677.movie_application;

/**
 * Created by 1691677 on 2/27/2018.
 */

public class Feed {
    public String original_title ;
    public String poster_path;
    public String url;

    public Feed(String title, String pic,String url)
    {
        original_title = title;
        poster_path= pic;
        this.url = url;

    }
}

