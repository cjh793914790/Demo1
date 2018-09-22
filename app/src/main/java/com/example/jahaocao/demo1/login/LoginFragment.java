package com.example.jahaocao.demo1.login;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jahaocao.demo1.R;
import com.example.jahaocao.demo1.base.BaseFragment;
import com.example.jahaocao.demo1.login.login2.Login2Presenter;
import com.example.jahaocao.demo1.login.login2.LoginFragment2;
import com.example.jahaocao.demo1.login.login3.LoginFragment3;
import com.example.jahaocao.demo1.man.MainActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginFragment extends BaseFragment implements LoginContract.View {
    private LoginContract.Presenter mPresenter;
    private TextView mTvSendCode;

    private EditText mEtvPhoneNumber;
    private EditText mEtvVerificationCode;

    private CheckBox mCbLisence;
    private ImageView mImageViewQQ;
    private ImageView mImageViewWx;
    private ImageView mImageViewWb;

    private LoginFragment2 loginFragment2 = new LoginFragment2();
    private LoginFragment3 loginFragment3 = new LoginFragment3();

    private Button mBtnLoggin;
    private TextView mTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login ,container,false );
        mTvSendCode = view.findViewById(R.id.login_btn_send_verification_code);

        mEtvPhoneNumber = view.findViewById(R.id.login_etv_phone_number);
        mEtvVerificationCode = view.findViewById(R.id.login_etv_verification_code);
        mTextView = (TextView) view.findViewById(R.id.login_login_tv_license);

        mCbLisence = view.findViewById(R.id.login_cb_license);
        mImageViewWx = view.findViewById(R.id.imageView);
        mImageViewQQ = view.findViewById(R.id.imageView2);
        mImageViewWb = view.findViewById(R.id.imageView3);

        mImageViewQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Toast.makeText(getContext(), "开始登录", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                        Login2Presenter presenter=new Login2Presenter();
                        ((LogActivity)getActivity()).addFragment(LoginFragment2.class,presenter,R.id.login_container,null,null);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Toast.makeText(getContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Toast.makeText(getContext(), "取消登录", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        initCheckBox();
       mImageViewWb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareConfig config = new UMShareConfig();
                config.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(getContext()).setShareConfig(config);
                UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.SINA, authListener);
            }
        });
       mImageViewWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareConfig config = new UMShareConfig();
                config.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(getContext()).setShareConfig(config);
                UMShareAPI.get(getContext()).getPlatformInfo(getActivity(), SHARE_MEDIA.WEIXIN, authListener);
            }
        });


        mBtnLoggin = view.findViewById(R.id.login_btn_login);

        mCbLisence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBtnLoggin.setEnabled(isChecked);
            }
        });

        mTvSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = mEtvPhoneNumber.getText().toString();
                //TODO 需要做手机号校验
                mPresenter.getVerificationCode(phoneNumber);
            }
        });


        mBtnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = mEtvPhoneNumber.getText().toString();
                String code = mEtvVerificationCode.getText().toString();
                mPresenter.login(phoneNumber, code);


            }
        });
        return view;

    }

    private void initCheckBox() {
        SpannableString spannableString = new SpannableString(getString(R.string.login_licence));
        //1 你要更换的颜色     2 3 字符串第几个个数到几个  4 没研究明白
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#00a0ff")), 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //1 点击事件 2 3 字符串第几个个数到几个 4 没研究明白
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.bgColor = Color.parseColor("#fffffb");
            }
        }, 7, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //不设置的话不会点击 不会动
        //1 你要更换的颜色     2 3 字符串第几个个数到几个  4 没研究明白
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#00a0ff")), 14, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //1 点击事件 2 3 字符串第几个个数到几个 4 没研究明白
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.bgColor = Color.parseColor("#fffffb");
            }
        }, 14, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//不设置的话不会点击 不会动
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());

        mTextView.setText(spannableString);


    }

    @Override
    public void verificationCodeSuccess() {
        mTvSendCode.setText("验证码发送成功");
    }

    @Override
    public void verificationCodeFail() {
        mTvSendCode.setText("验证码发送失败");
    }

    @Override
    public void loginSuccess() {

        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
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

    @Override
    public void loginFail(String msg) {
        Toast.makeText(getContext(), "登录失败" +msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

        mPresenter = presenter;

        mPresenter.attachView(this);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Login2Presenter presenter=new Login2Presenter();
            ((LogActivity)getActivity()).addFragment(LoginFragment2.class,presenter,R.id.login_container,null,null);
           /* getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.fragment_right_in,
                            R.anim.fragment_left_out,
                            R.anim.fragment_left_in,
                            R.anim.fragment_right_out
                    ).replace(R.id.login_container,loginFragment2)
                    .addToBackStack(null)
                    .commit();*/
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getContext(), "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getContext(), "取消了", Toast.LENGTH_LONG).show();
        }

    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getContext()).onActivityResult(requestCode, resultCode, data);

    }

    private void DongTaiShare() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS, Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(getActivity(), mPermissionList, 123);
        }

    }
}
