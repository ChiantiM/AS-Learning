package com.example.activitytest.filepersistencetest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.edit);
        Log.d("MainActivity", "onCreate");
        String inputtext = load();
        if(!TextUtils.isEmpty(inputtext)){
            edit.setText(inputtext);
            edit.setSelection(inputtext.length());
            Toast.makeText(this, "Restoring succeeded",
                    Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText = edit.getText().toString();
        save(inputText);

    }

    /**
     * 保存到文件
     * @param input
     */
    public void save(String input){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);
            Log.d("MainActivity", "save success");

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    /**
     * 从文件中读取
     * @return
     */
    public String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while((line = reader.readLine())!= null){
                content.append(line);
            }
            Log.d("MainActivity", "load success");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return content.toString();
    }
}
