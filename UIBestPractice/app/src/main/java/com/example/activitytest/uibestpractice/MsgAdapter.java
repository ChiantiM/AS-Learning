package com.example.activitytest.uibestpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lingnanmiao on 2017/7/23.
 */

public class MsgAdapter extends ArrayAdapter<Msg> {
    private int resourceId;

    public MsgAdapter(Context context, int resourceId, List<Msg> objects){
        super(context, resourceId, objects);
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Msg msg =  getItem(position);
        View view;
        ViewHolder  viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.leftlayout = (LinearLayout) view.findViewById(R.id.left_layout);
            viewHolder.rightlayout = (LinearLayout) view.findViewById(R.id.right_layout);
            viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
            viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (msg.getType() == Msg.TYPE_RECEIVED){
            viewHolder.leftlayout.setVisibility(View.VISIBLE);
            viewHolder.rightlayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        }
        else if(msg.getType() == Msg.SENT){
            viewHolder.rightlayout.setVisibility(View.VISIBLE);
            viewHolder.leftlayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return view;

    }

    class ViewHolder{
        LinearLayout leftlayout;
        LinearLayout rightlayout;
        TextView leftMsg;
        TextView rightMsg;

    }
}
