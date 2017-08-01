package com.example.activitytest.uibestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    //先声明所有控件和变量
    private List<Msg> msgList = new ArrayList<Msg>();
    private MsgAdapter adapter;

    private EditText inputtext;
    private Button send;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initMsgs();
        adapter = new MsgAdapter(MainActivity.this, R.layout.msg_item, msgList);
        inputtext = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.msg_list_view);
        listView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputtext.getText().toString();
                if(!"".equals(content)){
                    Msg msg= new Msg(content, Msg.SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(msgList.size());
                    inputtext.setText("");
                }
            }
        });


    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED); msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.SENT); msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED); msgList.add(msg3);
    }
}
