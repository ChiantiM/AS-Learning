package com.example.activitytest.broadcastbestpractice;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lingnanmiao on 2017/7/26.
 */

public class BasicActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstancestate){
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_main);
        ActivityCollector.addActivity(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
