package com.example.activitytest.contactstest;

/*
Last Editing: Aug 29 00:46
Last Running: Aug 29 00:46
Status: Running Success
 */


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    /**
     * 所用控件
     */
    ListView contactListview;
    ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListview = (ListView)findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
        contactListview.setAdapter(adapter);
        readContacts();//put contacts into list

    }

    public void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
            while (cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(displayName + "\n" + number);

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){cursor.close();}
        }

    }

}
