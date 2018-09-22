package com.example.jahaocao.demo1.base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T baseView);

}
