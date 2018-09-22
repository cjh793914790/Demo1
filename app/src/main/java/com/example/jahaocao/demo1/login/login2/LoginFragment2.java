package com.example.jahaocao.demo1.login.login2;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.base.BaseFragment;
import com.example.jahaocao.demo1.data.source.UserDataRepository;
import com.example.jahaocao.demo1.login.LogActivity;
import com.example.jahaocao.demo1.login.LoginFragment;
import com.example.jahaocao.demo1.login.login3.LoginFragment3;
import com.example.jahaocao.demo1.login.LoginPresenter;
import java.util.Random;


public class LoginFragment2 extends BaseFragment implements Login2Contract.View {
    Random random=new Random();
    private ImageView mBt_x1;
    private ImageView fh;
    private ImageView mImagViewTx;
    private EditText mTv_yhm;
    private ImageView mTl;
    private TextView mTv_tg;
    private LoginFragment3 loginFragment3 = new LoginFragment3();
    private Login2Contract.Presenter mPresenter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.login2, container, false);
        mBt_x1 = (ImageView) view.findViewById(R.id.mBt_X1);
        fh = (ImageView) view.findViewById(R.id.fh);
        mImagViewTx = (ImageView) view.findViewById(R.id.mImagViewTx);
        mTv_yhm = (EditText) view.findViewById(R.id.mTv_Yhm);
        mTl = (ImageView) view.findViewById(R.id.mTl);
        mTv_tg = (TextView) view.findViewById(R.id.mTv_tg);
        initYhmDl();
        initDl();
        initClose();
        //关闭
        mBt_x1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initClose();
            }
        });
        //返回
        fh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initClose();

            }
        });
        //头像
        mImagViewTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())

                        .setPositiveButton("相机", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AlertDialog.Builder(getContext())
                                        .setMessage("是否添加网络权限")
                                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                mPresenter.DongTaiShare();
                                                mPresenter.getXiangJi();
                                            }
                                        })

                                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getContext(), "未添加网络权限不能使用相机", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .create().show();

                            }
                        })

                        .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                        @Override
                         public void onClick(DialogInterface dialog, int which) {
                            mPresenter.getXiangCe();
                             }
                        })
                        .create().show();
        }
        });
        return view;
    }


    //跳过
    private void initYhmDl() {
        mTv_tg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTv_yhm.getText().toString().equals("")){
                    final String Mrname="user"+(10000000+random.nextInt(90000000));
                    new AlertDialog.Builder(getContext())
                            .setIcon(android.R.drawable.btn_star)
                            .setMessage("确认使用默认用户名 user"+Mrname+"?")
                            .setNeutralButton("使用", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .setCustomAnimations(
                                                    R.anim.fragment_right_in,
                                                    R.anim.fragment_left_out,
                                                    R.anim.fragment_left_in,
                                                    R.anim.fragment_right_out
                                            ).replace(R.id.login_container,loginFragment3)
                                            .addToBackStack(null)
                                            .commit();
                                }
                            })
                            .setNegativeButton("去修改", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    Toast.makeText(getContext(),"输入用户名", Toast.LENGTH_SHORT).show();
                                }
                            }).show();// show很关键

                }else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(
                                    R.anim.fragment_right_in,
                                    R.anim.fragment_left_out,
                                    R.anim.fragment_left_in,
                                    R.anim.fragment_right_out
                            ).replace(R.id.login_container,loginFragment3)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
    //登录
   void initDl() {
       mTl.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mPresenter.getYhm(mTv_yhm.getText().toString());
           }
       });
   }
   //关闭返回
   void initClose(){
       LoginPresenter loginPresenter = new LoginPresenter(UserDataRepository.getInstance());
       ((LogActivity)getActivity()).addFragment(LoginFragment.class,loginPresenter,R.id.login_container,null,null);
    }
    //设置权限
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode,resultCode,data);

    }
    //添加动态权限
    private void DongTaiShare() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS, Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(getActivity(), mPermissionList, 123);
        }
    }
    //相机图片数据
    @Override
    public void CameraPhotoBitmap(Bitmap bm) {
        Glide.with(this).load(bm).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mImagViewTx);

    }
    //相册图片数据
    @Override
    public void AlbumPhotoUrl(Bitmap bm) {
        Log.e("sdasdas",bm.toString());
        Glide.with(this).load(bm).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mImagViewTx);
    }

    @Override
    public void CodeSuccess(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(Login2Contract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }
}
