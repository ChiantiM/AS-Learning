package com.example.activitytest.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    //记得要初始化！！！！！！！！！！！！！
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadCastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadCastManager = LocalBroadcastManager.getInstance(this);//获取实例
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.activitytest.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadCastManager.registerReceiver(localReceiver, intentFilter);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent("com.example.activitytest.broadcasttest.LOCAL_BROADCAST");
                localBroadCastManager.sendBroadcast(intent);
            }
        });

    }

    @Override public void onDestroy(){
        super.onDestroy();
        localBroadCastManager.unregisterReceiver(localReceiver);
    }


    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent){
            Toast.makeText(context, "Local Receiver received", Toast.LENGTH_SHORT).show();

        }
    }
}
