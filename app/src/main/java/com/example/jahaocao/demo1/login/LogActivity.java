package com.example.jahaocao.demo1.login;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.StatusBarManager;
import com.example.jahaocao.demo1.base.BaseActivity;
import com.example.jahaocao.demo1.data.source.UserDataRepository;
import com.umeng.socialize.UMShareAPI;

public class LogActivity extends BaseActivity {


    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarManager.lightStatusBar(this);
        StatusBarManager.setStatusBarColor(this, ContextCompat.getColor(this,R.color.colorBs));
        setContentView(R.layout.activity_login);
        LoginPresenter loginPresenter = new LoginPresenter(UserDataRepository.getInstance());
        addFragment(LoginFragment.class, loginPresenter, R.id.login_container, null, null);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}