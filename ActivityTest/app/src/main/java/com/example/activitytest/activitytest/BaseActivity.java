package com.example.activitytest.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by lingnanmiao on 2017/7/18.
 */

public class BaseActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity", getClass().getSimpleName());
    }
}
