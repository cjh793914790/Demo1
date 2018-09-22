package com.example.jahaocao.demo1.login;

import android.util.Log;
import android.widget.Toast;

import com.example.jahaocao.demo1.data.User;
import com.example.jahaocao.demo1.data.source.UserDataSource;
import com.example.jahaocao.demo1.utils.Logger;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private UserDataSource mUserDataSource;

    public LoginPresenter(UserDataSource userDataSource){

        mUserDataSource = userDataSource;
    }

    @Override
    public void getVerificationCode(String phoneNumber) {
        Map<String,String> params = new HashMap<>();

        params.put("phone", phoneNumber);
        params.put("smsType", "0");
        mUserDataSource.sendVerificationCode((RxFragment) mView, params, new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                Logger.d("onSubscribe");
            }

            @Override
            public void onNext(Object o) {

                mView.verificationCodeSuccess();
            }

            @Override
            public void onError(Throwable e) {
                mView.verificationCodeFail();
                Logger.d("onError");
            }

            @Override
            public void onComplete() {
                Logger.d("onComplete");
            }
        });
    }

    @Override
    public void login(String phoneNumber, String code) {
        Map<String,String> params = new HashMap<>();
        params.put("phone", phoneNumber);
        params.put("verificationCode", code);
        params.put("platform", "1");
        params.put("appVersion", "1.0.0");
        params.put("deviceId", "1454564545");
        mUserDataSource.login((RxFragment)mView, params, new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                mView.loginSuccess();
                Log.e("adad",user.getComments()+"");
            }
//            13069320736
            //114522
            @Override
            public void onError(Throwable e) {
                Log.e("onErrors: ",e.getMessage() );
            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Override
    public void attachView(LoginContract.View baseView) {
        mView=baseView;
    }
}
