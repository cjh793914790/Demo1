package com.example.jahaocao.demo1.login;


import com.example.jahaocao.demo1.base.BasePresenter;
import com.example.jahaocao.demo1.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<LoginContract.Presenter> {
        void verificationCodeSuccess();
        void verificationCodeFail();


        void loginSuccess();

        void loginFail(String msg);
    }
    interface Presenter extends BasePresenter<LoginContract.View> {

        void getVerificationCode(String phoneNumber);

        void login(String phoneNumber,String code);
    }
}
