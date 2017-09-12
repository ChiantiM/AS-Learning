package com.example.activitytest.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private MyDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is used to get database
        dbhelper = new MyDatabaseHelper(this, "BookStor.db", null, 1);

        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbhelper.getWritableDatabase();
            }
        });

        Button replaceData = findViewById(R.id.replace_data);
        replaceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                //Start Transaction
                db.beginTransaction();
                try{
                    db.delete("BookStor.db", null, null);
                    if (true) {
                    // 在这里手动抛出一个异常，让事务失败
                        throw new NullPointerException();
                    }
                    ContentValues values = new ContentValues();
                    values.put("name", "Game of Thrones");
                    values.put("author", "George Martin");
                    values.put("pages", 720);
                    values.put("price", 20.85);
                    Toast.makeText(MainActivity.this, "Replace successful", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                } finally {
                    db.endTransaction(); // 结束事务
                }
            }
        });
    }

}
