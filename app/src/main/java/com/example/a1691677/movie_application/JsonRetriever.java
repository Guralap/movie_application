package com.example.a1691677.movie_application;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by 1691677 on 2/27/2018.
 */


    public class JsonRetriever {

        public static void RetrieveFromURL(final CallBackMe whoToCall, String url)
        {
            RetrieveFromURL((Context)whoToCall, url, whoToCall);
        }

        public static void RetrieveFromURL(Context context, String url, final CallBackMe whoToCall)
        {
            StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String ss) {
                    whoToCall.CallThis(ss);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    //Throw some error here.

                }
            });

            RequestQueue rQueue = Volley.newRequestQueue(context);
            rQueue.add(request);
        }
    }
