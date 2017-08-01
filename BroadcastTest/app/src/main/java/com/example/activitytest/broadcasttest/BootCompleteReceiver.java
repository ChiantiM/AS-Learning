package com.example.activitytest.broadcasttest;

import android.app.admin.ConnectEvent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by lingnanmiao on 2017/7/26.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show();
    }
}
