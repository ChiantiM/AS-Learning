package com.example.activitytest.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*
这是自己打的代码。
debug很久就是switch时case直接break返回了空值。
switch的逻辑还是要清楚，不break会一个一个case往下走。

通过。
 */
public class MainActivity extends AppCompatActivity {
    private Button update_data;
    private Button add_data;
    private Button query_data;
    private Button delete_data;
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String content_dir = "content://com.example.activitytest.databasetest.provider/book";

        add_data = (Button)findViewById(R.id.add_data);
        query_data = (Button)findViewById(R.id.query_data);
        update_data= (Button)findViewById(R.id.update_data);
        delete_data =(Button)findViewById(R.id.delete_data);

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(content_dir);
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                Log.d("MainActivity", "\nNewUri: " + newUri + "\n"
                        + "id: "+ newId);
            }
        });


        query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(content_dir);
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor!= null){
                    while (cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.
                                getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    }
                    cursor.close();
                }

            }
        });

        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(content_dir + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 24.05);
                int rows = getContentResolver().update(uri, values, null, null);
            }
        });

        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(content_dir + "/"+newId);
                int rows = getContentResolver().delete(uri, null, null);
                Log.d("MainActivity:", "\nURI: " + uri
                        +" \nDelete " + rows +" rows");
            }
        });







    }
}
