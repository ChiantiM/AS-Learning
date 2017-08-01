package com.example.activitytest.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lingnanmiao on 2017/7/20.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceID;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context, textViewResourceId, objects);
        resourceID = textViewResourceId;
    };

    @Override
    public View getView(int position, View convertview, ViewGroup parent){
        Fruit fruit = getItem(position);//从列表中获取一项Fruit
        View view;
        ViewHolder viewholder;


        if (convertview == null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewholder = new ViewHolder();
            ImageView imageView = (ImageView) view.findViewById(R.id.fruit_image);
            TextView textView = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewholder);
        }
        else {
            view = convertview; //缓存的view
            viewholder = (ViewHolder) view.getTag();
        }

        viewholder.fruitImage.setImageResource(fruit.getId());
        viewholder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}
