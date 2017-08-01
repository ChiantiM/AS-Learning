package com.example.activitytest.fragmentbestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by lingnanmiao on 2017/7/26.
 */

public class NewsAdapter  extends ArrayAdapter<News> {
    private int resourceID;

    public NewsAdapter(Context context, int resourceID, List<News> objects){
        super(context, resourceID, objects);
        this.resourceID = resourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        News news = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, null);
        }
        else {view = convertView;}
        TextView news_title = (TextView) view.findViewById(R.id.news_title);
        news_title.setText(news.getTitle());
        return view;
    }

}
