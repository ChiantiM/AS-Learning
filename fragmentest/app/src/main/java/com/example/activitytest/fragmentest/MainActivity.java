package com.example.activitytest.fragmentest;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by lingnanmiao on 2017/7/24.
 */

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AnotherRightFragment fragment = new AnotherRightFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager.
//                        beginTransaction();
//                transaction.replace(R.id.right_layout, fragment);
//                transaction.commit();
//            }
//        });
    }

}
