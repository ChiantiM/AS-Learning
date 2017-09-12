package com.chinacolor.chinesetraditionalcolors;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lingnanmiao on 9/12/17.
 */

public class ColorAdapter extends ArrayAdapter<Color> {
    private int itemLayoutid;

    public ColorAdapter(Context context, int layoutid, List<Color> objs){
        super(context, layoutid, objs);
        itemLayoutid = layoutid;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Color color = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (true){
            view = LayoutInflater.from(getContext()).inflate(itemLayoutid, null);
            viewHolder = new ViewHolder();
            ImageView color_image = (ImageView) view.findViewById(R.id.color_image);
            TextView color_name = (TextView)view.findViewById(R.id.color_text);

            GradientDrawable myGrad = (GradientDrawable)color_image.getBackground();
            myGrad.setColor(color.getColorValue());

            color_name.setText(color.getColorName());

            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        //viewHolder.colorValue.setBackgroundColor(0xff000000);
        //viewHolder.colorName.setText(color.getColorName());


        return view;

    }


    class ViewHolder{
        ImageButton colorValue;
        TextView colorName;
    }
}
