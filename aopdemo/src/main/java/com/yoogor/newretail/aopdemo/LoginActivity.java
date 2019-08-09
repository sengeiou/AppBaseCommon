package com.yoogor.newretail.aopdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yanzhenjie.permission.runtime.Permission;
import com.yoogor.newretail.aopdemo.annotations.AfterPermission;
import com.yoogor.newretail.aopdemo.annotations.AndPermissionEx;
import com.yoogor.newretail.aopdemo.annotations.BeforePermission;
import com.yoogor.newretail.aopdemo.enums.PermissionType;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @AndPermissionEx(type = PermissionType.RUNTIME, permissions = {Permission.READ_EXTERNAL_STORAGE,Permission.CAMERA})
    public void Click(View view) {
        Log.e("tag", "activity-Click");
    }

    @BeforePermission
    public void beforePermission() {
        Log.e("tag", "activity-beforePermission");
    }

    @AfterPermission
    public void afterPermission() {
        Log.e("tag", "activity-afterPermission");
    }

}
