package com.example.jahaocao.demo1.login.login2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.jahaocao.demo1.base.BasePresenter;
import com.example.jahaocao.demo1.base.BaseView;

public interface Login2Contract {
    interface Presenter extends BasePresenter<Login2Contract.View>{

    //获取相机图片
    void getXiangJi();
    //获取动态权限
    void DongTaiShare();
    //获取相册图片
    void getXiangCe();
    //图片数据回调
    void onActivityResult(int requestCode, int resultCode, Intent data);
    //
    void getYhm(String string);


}
    interface View extends BaseView<Login2Contract.Presenter> {
        void CameraPhotoBitmap(Bitmap bm);
        void AlbumPhotoUrl(Bitmap bm);
        void  CodeSuccess(String string);


    }

}
