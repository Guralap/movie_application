package com.example.a1691677.movie_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 1691677 on 2/27/2018.
 */
public class CustomFeedAdapter extends ArrayAdapter<Feed> {

    public CustomFeedAdapter(Context context, ArrayList<Feed> objects) {
        super(context, R.layout.resource, objects);
        //Context is the context we are in... For our example it will be within a listview.
        //Layout: we are going to make a layout with feed in mind.
        //Objects: This will be whatever else we need to pass to it.
    }

    //This fun
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflator = LayoutInflater.from(getContext());  //Instantiates a layout XML file into its corresponding object
        //In our test case it would our ListView object. Context will change where you use.
        //Think of it as prepare data.

        View customFeedView = myInflator.inflate(R.layout.resource, parent, false);

        /***Think of the inflator part as in it converts the xml layout into a widget!*****/
        ////In our case, it is adding that feed to the listview

        //Get our widgets
        TextView myText = customFeedView.findViewById(R.id.textView);
        ImageView myImage =customFeedView.findViewById(R.id.imageView);


        //Get our values
        String title = getItem(position).original_title; //GetItem gets the current item within the array.
        String pic = getItem(position).poster_path;
        String url = getItem(position).url;
        //As that item is a custom object we made.
        //We have access to message and picture.
        //Set our values
        myText.setText(title);
        new DownloadImageTask(myImage)
                .execute(pic);


        return customFeedView;  //Sending the view back, in this case as a row.
    }


}