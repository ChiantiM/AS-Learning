package com.example.activitytest.sharedpreferencestest;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button_save);
        Button load = (Button) findViewById(R.id.button_load);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("TestSP", 0);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("a","1");
                editor.putInt("b",2);
                editor.putBoolean("c", false);

                editor.commit();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("TestSP", 0);
                String a = sp.getString("a", "");
                int b = sp.getInt("b", 2);
                boolean c = sp.getBoolean("c", false);
            }
        });


    }
}
