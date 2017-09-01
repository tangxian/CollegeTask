package com.ld.collegetask.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.yoojia.inputs.AndroidNextInputs;
import com.github.yoojia.inputs.Scheme;
import com.github.yoojia.inputs.WidgetAccess;
import com.github.yoojia.inputs.verifiers.NotEmptyVerifier;
import com.google.gson.Gson;
import com.ld.collegetask.AppApplication;
import com.ld.collegetask.MD5.Ecryption;
import com.ld.collegetask.R;
import com.ld.collegetask.RequestSerives;
import com.ld.collegetask.beans.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private AppApplication app;
    private String tag = "LoginAct";
    private EditText et_uname, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = (AppApplication) getApplication();
        findView();
    }

    private void findView(){
        et_uname = (EditText) findViewById(R.id.et_uname);
        et_pass = (EditText) findViewById(R.id.et_pass);
    }

    public void onlogin_click(View v){
//        System.out.println("登录：" + et_uname.getText() + "|" + et_pass.getText());
        final AndroidNextInputs inputs = new AndroidNextInputs();
        final WidgetAccess access = new WidgetAccess(this);
        // 一、流式API
        inputs.add(access.findEditText(R.id.et_uname)).with(new Scheme(new NotEmptyVerifier()).msg("用户名不能为空"))
                .add(access.findEditText(R.id.et_pass)).with(new Scheme(new NotEmptyVerifier()).msg("密码不能为空"));
        if (inputs.test()){
            login(et_uname.getText().toString(), Ecryption.getMD5(et_pass.getText().toString()));
        }
    }

    private void login(String uname, String passwd){
//        创建一个Retrofit 对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppApplication.server)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)，json转java对象，不支持jsonp
                //.addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RequestSerives requestSerives = retrofit.create(RequestSerives.class);//这里采用的是Java的动态代理模式
        Call<String> call = requestSerives.login(AppApplication.sTag, uname, passwd, "1");//传入我们请求的键值对的值
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e(tag,"return:" + response.body().toString());
                //1.jsonp变成json
                String jsonp = response.body().toString();
                String json = jsonp.substring(8,jsonp.length() - 1);
                //2.转java对象
                Gson gson = new Gson();
                User user = gson.fromJson(json, User.class);
                app.setUser(user);//把User对象写到全局变量
                //3.使用数据
                String msg = "";
                switch (app.getUser().getStatus()){
                    case "-2": msg = "用户不存在";
                        break;
                    case "0": msg = "用户名或密码错误";
                        break;
                    case "1": msg = "登录成功";
                        finish();
                        break;
                    default: msg = "登录失败";
                }
                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i(tag,"请求失败");
                Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
