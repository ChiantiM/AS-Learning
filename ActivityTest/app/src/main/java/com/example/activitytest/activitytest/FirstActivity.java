package com.example.activitytest.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lingnanmiao on 2017/7/17.
 */

public class FirstActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("FirstActivity","task id is" + getTaskId());
        requestWindowFeature(Window.FEATURE_CONTEXT_MENU);//可以隐藏标题,要在setContentView前执行
        setContentView(R.layout.first_layout);

        Button button1 = (Button) findViewById(R.id.button_1);
        //注册一个监听器
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                String return_data = data.getStringExtra("data_return");
                Log.d("FirstActivity", return_data);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT);
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked remove", Toast.LENGTH_SHORT);
                break;
            default:
        }
        return true;
    }
}
