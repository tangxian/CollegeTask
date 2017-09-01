package com.ld.collegetask.main;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.ld.collegetask.AppApplication;
import com.ld.collegetask.R;
import com.ld.collegetask.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private AppApplication app;
    private String tag = "MainAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (AppApplication) getApplication();
        app.getPhoneInfo();

    }


    public void login_Onclick (View view){
        Log.i(tag, "点击登录");
        Intent it = new Intent(MainActivity.this, LoginActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(it);
        //finish();
    }
}
