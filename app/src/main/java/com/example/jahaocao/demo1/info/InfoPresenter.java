package com.example.jahaocao.demo1.info;

import android.util.Log;

import com.example.jahaocao.demo1.data.Comment;
import com.example.jahaocao.demo1.data.Info;
import com.example.jahaocao.demo1.data.Relevant;
import com.example.jahaocao.demo1.data.source.NewsDataSource;
import com.example.jahaocao.demo1.data.source.UserDataSource;
import com.example.jahaocao.demo1.data.source.remote.NewsRemoteDataSource2;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class InfoPresenter implements InfoContract.Presenter {


    private UserDataSource newsDataSource;
    private InfoContract.View mView;

    public InfoPresenter(UserDataSource source2) {
        newsDataSource =source2;
    }

    @Override
    public void setInfo(String s1, String s2) {
        Map<String,String> params = new HashMap<>();

        params.put("newsId", s1);
        params.put("userId", "d56ea66e7ee741f498ca51242c8c6394");
        newsDataSource.getInfo((RxAppCompatActivity) mView, params, new Observer<Info>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Info info) {
                mView.getInfo(info);
            }

            @Override
            public void onError(Throwable e) {
                Log.e( "onError2: ",e.getMessage() );
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setRelevant(String s1) {
        Map<String,String> params = new HashMap<>();
        params.put("newsId", s1);
        newsDataSource.getRelevant((RxAppCompatActivity) mView, params, new Observer<List<Relevant>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Relevant> info) {
                mView.getRelevant(info);
                Log.e("fh",info.get(0).getTitle());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("123",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setComment(String s1, String s2,String s3) {
        Map<String,String> params = new HashMap<>();
        params.put("objectId", s1);
        params.put("objectType", s2);
        params.put("cursor", s3);
        newsDataSource.getComment((RxAppCompatActivity) mView, params, new Observer<Comment>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Comment info) {
                mView.getComment(info);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("1233",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void setAddComment(String userId, String objectId, String objectType, String content) {
        Map<String,String> params = new HashMap<>();

        params.put("userId", userId);
        params.put("objectId", objectId);
        params.put("objectType", objectType);
        params.put("content", content);
        newsDataSource.getAddComment((RxAppCompatActivity) mView, params, new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object info) {

            }

            @Override
            public void onError(Throwable e) {
                mView.getAddComment("成功");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void attachView(InfoContract.View baseView) {
            mView=baseView;
    }
}
