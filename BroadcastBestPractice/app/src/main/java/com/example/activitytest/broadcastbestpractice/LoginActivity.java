package com.example.activitytest.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BasicActivity {
    private CheckBox checkBox;
    private EditText account;
    private EditText password;
    private Button login;

    private SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        checkBox = (CheckBox) findViewById(R.id.remember_pass);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("remember_password", false);

        if(isRemember){
            String accounttext = pref.getString("account", "");
            String passwordtext= pref.getString("password", "");
            account.setText(accounttext);
            password.setText(passwordtext);
            checkBox.setChecked(true);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accounttext = account.getText().toString();
                String passwordtext = password.getText().toString();

                if (accounttext.equals("admin") && passwordtext.equals("123456")) {
                    SharedPreferences.Editor editor = pref.edit();
                    if (checkBox.isChecked()){
                        editor.putString("account", account.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.putBoolean("remember_password", true);
                        Log.d("LoginActivity", "pref edit success");
                    }
                    else{
                        editor.clear();
                        Log.d("LoginActivity", "pref clear success");
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
                if(Build.VERSION.SDK_INT>=23){
                    if (!Settings.canDrawOverlays(LoginActivity.this)) {
                        Intent intent1 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    }
                }
            }
        });


    }
}

