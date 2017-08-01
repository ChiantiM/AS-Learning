package com.example.activitytest.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by lingnanmiao on 2017/7/26.
 */

public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "AnotherBroadcastReceiver received", Toast.LENGTH_SHORT).show();
    }
}
