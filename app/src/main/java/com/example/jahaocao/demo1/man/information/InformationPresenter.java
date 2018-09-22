package com.example.jahaocao.demo1.man.information;


import android.util.Log;

import com.example.jahaocao.demo1.base.MyObserver;
import com.example.jahaocao.demo1.data.Datas;
import com.example.jahaocao.demo1.data.Datass;
import com.example.jahaocao.demo1.data.NewsData;
import com.example.jahaocao.demo1.data.Titles;
import com.example.jahaocao.demo1.data.source.UserDataSource;
import com.example.jahaocao.demo1.utils.IoSetGet;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InformationPresenter implements  InformationContract.Presenter {
    public static Map<String,List<Datas.NewListBean>> map=new HashMap<>();
    public static String string;
    private InformationContract.View mView;
    private UserDataSource mUserDataSource;
    public InformationPresenter(UserDataSource newsDataSource){
        mUserDataSource = newsDataSource;
    }

    @Override
    public void setAdd(String s1,String s3) {
        Map<String,String> params = new HashMap<>();
        params.put("userId", s3);
        params.put("channelId", s1);
        params.put("cursor", s3);
        mUserDataSource.getAdd((RxFragment) mView, params, new Observer<Datass>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(Datass datas) {
                mView.getAdd(datas);

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setTitle() {

        mUserDataSource.getTitle((RxFragment)mView, new Observer<Titles>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("ss","哈哈");

            }
            @Override
            public void onNext(Titles titles) {
                mView.getCodeSuccess();
                mView.getTitles(titles);
            }
            @Override
            public void onError(Throwable e) {

                mView.getCodeFail();
            }
            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setView( String s2) {
        Map<String,String> params = new HashMap<>();
        params.put("userId", "d56ea66e7ee741f498ca51242c8c6394");
        params.put("channelId", s2);
        params.put("cursor", "0");
//        mUserDataSource.getData((RxFragment) mView, params, new Observer<Datas>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//            @Override
//            public void onNext(Datas datas) {
//                mView.getData(datas);
//
//            }
//            @Override
//            public void onError(Throwable e) {
//
//            }
//            @Override
//            public void onComplete() {
//
//            }
//        });


    }

    @Override
    public void attachView(InformationContract.View baseView) {
        mView=baseView;
    }
}
