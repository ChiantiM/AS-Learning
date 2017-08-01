package com.example.activitytest.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

/**
 * Created by lingnanmiao on 2017/7/17.
 */

public class ThirdActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("ThirdActivity", "Task id is " + getTaskId());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.third_layout);
    }


}
