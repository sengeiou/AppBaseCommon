package com.yoogor.newretail.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @LoginCheck(value = "登录",type = 1)
    public void checkLogin(View view){
        Toast.makeText(this,"登录",Toast.LENGTH_SHORT).show();
        getCache(5);
    }

    @LoginCheck(value = "缓存",type = 3)
    public void getCache(int cache){
        Toast.makeText(this,cache + "",Toast.LENGTH_SHORT).show();
    }

    @LoginCheck(value = "非登录",type = 2)
    public void unCheckLogin(View view){
        Toast.makeText(this,"非登录",Toast.LENGTH_SHORT).show();
    }
}
