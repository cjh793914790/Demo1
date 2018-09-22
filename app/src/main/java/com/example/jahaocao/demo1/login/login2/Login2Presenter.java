package com.example.jahaocao.demo1.login.login2;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.login.login3.LoginFragment3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Login2Presenter implements Login2Contract.Presenter{
    private Login2Contract.View mView;
    private LoginFragment2 loginFragment2=new LoginFragment2();
    private LoginFragment3 loginFragment3=new LoginFragment3();

    @Override
    public void getXiangJi() {
        //动态权限：点击相机时获取相机权限
        DongTaiShare();
        //从相机获取图片
        getPicFromCamera();

    }

    @Override
    public void getXiangCe() {
        Log.e("sss","出来啊");
        getPicFromAlbm();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            // 调用相机后返回
            case 1:
                if (resultCode == ((Fragment)mView).getActivity().RESULT_OK) {
                    final Bitmap photo = intent.getParcelableExtra("data");
                    //给头像设置你相机拍的照片
                   mView.CameraPhotoBitmap(photo);

                }
                break;
            //调用相册后返回
            case 2:
                if (resultCode == ((Fragment)mView).getActivity().RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);//裁剪图片
                }
                break;
            //调用剪裁后返回
            case 3:
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                   mView.AlbumPhotoUrl(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("userHeader", image);
                    File file = new File(path);
                    /*
                     *这里可以做上传文件的额操作
                     */
                }
                break;

        }
    }


    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        ((Fragment)mView).startActivityForResult(intent, 3);
    }

    /**
     * 保存图片到本地
     *
     * @param name
     * @param bmp
     * @return
     */
    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //调用相册
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        ((Fragment)mView).startActivityForResult(photoPickerIntent, 2);

    }
    //调用系统相机
    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ((Fragment)mView). startActivityForResult(intent, 1);
    }

        @Override
    public void attachView(Login2Contract.View baseView) {
        mView = baseView;
    }
    //添加动态权限
    public void DongTaiShare() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS, Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(((Fragment)mView).getActivity(), mPermissionList, 123);
        }
    }
    //对用户名的操作
    @Override
    public void getYhm(String string) {
        if (string.equals("")){
            mView.CodeSuccess("请输入用户名");
        }else {
            ((Fragment)mView).getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.fragment_right_in,
                            R.anim.fragment_left_out,
                            R.anim.fragment_left_in,
                            R.anim.fragment_right_out
                    ).replace(R.id.login_container, loginFragment3)
                    .addToBackStack(null)
                    .commit();
        }
    }
}